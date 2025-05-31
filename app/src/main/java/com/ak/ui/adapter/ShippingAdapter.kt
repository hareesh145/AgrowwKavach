package com.ak.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ak.databinding.ItemShippingAddressBinding
import com.ak.model.Shipping

class ShippingAdapter(val shippingList: List<Shipping>?) : RecyclerView.Adapter<ShippingAdapter.ShippingViewHolder>() {

    // Define your ViewHolder and other necessary methods here
     inner class ShippingViewHolder(val binding : ItemShippingAddressBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(shipping: Shipping) {
            binding.apply {
                // Bind your shipping data to the views here
                name.text = shipping.name
                addressName.text = "${shipping.addressLine1?:""} ,${shipping.addressLine2?:""}"
                cityCountryCode.text = "${shipping.city} ,${shipping.state} ,${shipping.countryCode}"
                postalCode.text = shipping.postalCode
                // Add more bindings as necessary
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShippingViewHolder {
        return ShippingViewHolder(
            ItemShippingAddressBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShippingViewHolder, position: Int) {
        shippingList?.let { holder.bind(it[position]) } // Implement the bind method in your ViewHolder to bind data
    }

    override fun getItemCount(): Int {
        return shippingList?.size?:0
    }
}