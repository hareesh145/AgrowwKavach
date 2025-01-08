package com.ak.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ak.R
import com.ak.SharedPref
import com.ak.databinding.HomeScreenBinding
import com.ak.model.NavItem
import com.ak.ui.adapter.NavAdapter
import com.ak.viewmodel.AKViewModel
import com.ak.viewmodel.NetworkRepository

class HomeScreen : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navController: NavController
    private lateinit var binding: HomeScreenBinding
    val akViewModel = AKViewModel(NetworkRepository())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        SharedPref.getInstance(this).userProfile?.let {
            Log.d("TAG", "onCreate: $it")
        }

        val appBarConfiguration: AppBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home,
            R.id.navigation_store,
            R.id.navigation_varatha,
            R.id.navigation_cart,
            R.id.navigation_profile
        ).setOpenableLayout(binding.drawerLayout).build()

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setupWithNavController(binding.toolbar, navController, appBarConfiguration)
        setupWithNavController(binding.navView, navController)

        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toggle.isDrawerIndicatorEnabled = false
        binding.toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_menu)

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.toolbar.setNavigationOnClickListener {
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        val navItems = listOf(
            NavItem(R.drawable.ic_my_profile, "My Profile"),
            NavItem(R.drawable.ic_my_order, "My Order"),
            NavItem(R.drawable.ic_my_list, "My List"),
            NavItem(R.drawable.ic_shipping_address, "Shipping Address"),
            NavItem(R.drawable.ic_cc_address, "Credit Card Address"),
            NavItem(R.drawable.ic_payment_method, "Payment Method"),
            NavItem(R.drawable.saved_cart, "Saved Cart"),
            NavItem(R.drawable.ic_about_us, "About Us"),
            NavItem(R.drawable.ic_contact_us, "Contact Us")
        )
        binding.navHeader.navItemIcon.setImageResource(R.drawable.ic_my_profile)
        binding.navHeader.navItemTitle.text =
            "${SharedPref.getInstance(this).userProfile?.firstName} ${SharedPref.getInstance(this).userProfile?.lastName}"
        // Initialize RecyclerView
        binding.navRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.navRecyclerView.adapter = NavAdapter(navItems) { selectedItem ->
            handleNavItemClick(selectedItem)
        }
    }

    private fun handleNavItemClick(selectedItem: NavItem) {
        when (selectedItem.title) {
            "My Profile" -> {
                Log.d("TAG", "handleNavItemClick: My Profile")
            }

            "My Order" -> {
                Log.d("TAG", "handleNavItemClick: My Order")
            }

            "My List" -> {
                Log.d("TAG", "handleNavItemClick: My List")
            }

            "Shipping Address" -> {
                Log.d("TAG", "handleNavItemClick: Shipping Address")
            }

            "Credit Card Address" -> {
                Log.d("TAG", "handleNavItemClick: Credit Card Address")
            }

            "Payment Method" -> {
                Log.d("TAG", "handleNavItemClick: Payment Method")
            }
        }


    }

    fun navigateToNext(navigationID : Int) {
        navController.navigate(navigationID)
    }

    fun navigateToNext(navigationID : Int, bundle: Bundle) {
        navController.navigate(navigationID, bundle)
    }


}