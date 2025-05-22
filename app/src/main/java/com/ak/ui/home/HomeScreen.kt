package com.ak.ui.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ak.R
import com.ak.SharedPref
import com.ak.databinding.CustomTitleBarBinding
import com.ak.databinding.HomeScreenBinding
import com.ak.model.NavItem
import com.ak.ui.adapter.NavAdapter
import com.ak.viewmodel.AKViewModel
import com.ak.viewmodel.NetworkRepository
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeScreen : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navController: NavController
    private lateinit var binding: HomeScreenBinding
    val akViewModel = AKViewModel(NetworkRepository())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Log profile
        SharedPref.getInstance(this).userProfile?.let {
            Log.d("TAG", "onCreate: $it")
        }

        // Setup custom toolbar
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        val customTitleBarBinding = CustomTitleBarBinding.inflate(layoutInflater)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayShowCustomEnabled(true)
            setDisplayShowHomeEnabled(false)
            setDisplayHomeAsUpEnabled(false)
            customView = customTitleBarBinding.root
        }
        toolbar.navigationIcon = null // Hide default icon

        // Setup navController
        navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_home,
            R.id.navigation_store,
            R.id.navigation_varatha,
            R.id.navigation_cart,
            R.id.navigation_profile
        ).setOpenableLayout(binding.drawerLayout).build()

        // Setup drawer and bottom nav with navController
        setupWithNavController(binding.navView, navController)
        binding.navView.setupWithNavController(navController)

        // Manage nav icon and title manually
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isTopLevel = appBarConfiguration.topLevelDestinations.contains(destination.id)

            if (isTopLevel) {
                customTitleBarBinding.menuIcon.setImageResource(R.drawable.ic_menu)
                customTitleBarBinding.menuIcon.setOnClickListener {
                    if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        binding.drawerLayout.closeDrawer(GravityCompat.START)
                    } else {
                        binding.drawerLayout.openDrawer(GravityCompat.START)
                    }
                }
            } else {
                customTitleBarBinding.menuIcon.setImageResource(R.drawable.ic_back)
                customTitleBarBinding.menuIcon.setOnClickListener {
                    navController.navigateUp()
                }
            }
        }

        // Setup drawer header
        binding.navHeader.navItemTitle.text =
            "${SharedPref.getInstance(this).userProfile?.firstName} ${SharedPref.getInstance(this).userProfile?.lastName}"

        // Setup drawer nav items
        val navItems = listOf(
            NavItem(R.drawable.ic_my_profile, "My Profile"),
            NavItem(R.drawable.ic_my_order, "My Order"),
            NavItem(R.drawable.ic_my_list, "My List"),
            NavItem(R.drawable.ic_shipping_address, "Shipping Address"),
            NavItem(R.drawable.ic_cc_address, "Credit Card Address"),
            NavItem(R.drawable.ic_payment_method, "Payment Method"),
            NavItem(R.drawable.ic_saved_cart, "Saved Cart"),
            NavItem(R.drawable.ic_about_us, "About Us"),
            NavItem(R.drawable.ic_contact_us, "Contact Us")
        )
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