package com.ak.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ak.R
import com.ak.SharedPref
import com.ak.databinding.CustomTitleBarBinding
import com.ak.databinding.CustomTitleViewBinding
import com.ak.databinding.MyOrdersActivityBinding
import com.ak.ui.adapter.MyOrdersAdapter
import com.ak.viewmodel.MyOrdersViewModel
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import com.ak.util.Result
import com.ak.util.Utils

@AndroidEntryPoint
class MyOrderActivity : AppCompatActivity() {
    lateinit var binding: MyOrdersActivityBinding
    val myOrdersViewModel: MyOrdersViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyOrdersActivityBinding.inflate(layoutInflater).also {
            binding = it
            setContentView(it.root)
        }

        // Setup custom toolbar
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        val customTitleBarBinding = CustomTitleViewBinding.inflate(layoutInflater)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayShowCustomEnabled(true)
            setDisplayShowHomeEnabled(false)
            setDisplayHomeAsUpEnabled(false)
            customView = customTitleBarBinding.root
        }
        customTitleBarBinding.menuIcon.setImageResource(R.drawable.ic_back)
        customTitleBarBinding.customTitle.text = getString(R.string.my_orders)
        customTitleBarBinding.menuIcon.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        myOrdersViewModel.fetchMyOrders(JsonObject().apply {
            addProperty(
                "userId",
                SharedPref.getInstance(this@MyOrderActivity).userProfile.userId
            ) // Example user ID, replace with actual user ID
        })
        myOrdersViewModel.orders.observe(this) {
            when (it) {
                is Result.Loading -> {
                    // Show loading indicator
                    Utils.showProgessBar(this)
                }
                is Result.Success -> {
                    Utils.hideProgressBar()
                    // Hide loading indicator and update UI with orders
                    binding.productsList.adapter = MyOrdersAdapter(it.data.userOrders)
                }
                is Result.Error -> {
                    // Hide loading indicator and show error message
                    Utils.hideProgressBar()
                    Utils.showSnackBar(binding.root,it.message ?: "Error fetching orders")
                }
                Result.Idle -> {
                    // Do nothing
                    Utils.hideProgressBar()
                }
            }
        }
    }
}