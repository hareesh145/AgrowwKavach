package com.ak.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ak.databinding.ShippingAddressLayoutBinding
import com.ak.viewmodel.ShippingViewModel
import dagger.hilt.android.AndroidEntryPoint

import androidx.activity.viewModels
import com.ak.R
import com.ak.SharedPref
import com.ak.databinding.CustomTitleViewBinding
import com.ak.ui.MyOrderActivity
import com.ak.ui.dialogs.AddressBottomSheet
import com.google.gson.JsonObject

@AndroidEntryPoint
class MyShippingAddressActivity : AppCompatActivity() {
    lateinit var binding: ShippingAddressLayoutBinding
    val shippingViewModel: ShippingViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ShippingAddressLayoutBinding.inflate(layoutInflater).also {
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
        customTitleBarBinding.customTitle.text = getString(R.string.saved_addresses)
        customTitleBarBinding.menuIcon.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
        binding.addAddress.setOnClickListener {
            // Open the address bottom sheet dialog
            val addressBottomSheet = AddressBottomSheet()
            addressBottomSheet.show(supportFragmentManager, "AddressBottomSheet")
        }

        shippingViewModel.fetchShipping(JsonObject().apply {
            addProperty(
                "userId",
                SharedPref.getInstance(this@MyShippingAddressActivity).userProfile.userId
            ) // Example user ID, replace with actual user ID
        })
    }
}