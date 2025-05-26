package com.ak.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ak.databinding.ItemOrderBinding
import com.ak.model.UserOrders
import com.ak.util.Utils

class MyOrdersAdapter (
    private val orders: List<UserOrders>
) : RecyclerView.Adapter<MyOrdersAdapter.OrderViewHolder>() {

    class OrderViewHolder(val binding: ItemOrderBinding) : RecyclerView.ViewHolder(binding.root) {
       fun bind(order: UserOrders) {
            // Bind order data to the views
            binding.tvOrderId.text = "Order ID : ${order.ecomOrdersId}"
            binding.tvCreatedDate.text = "Created : ${Utils.convertDateFormatWithSpaceTime(order.createdDate)?:"-"}"
            binding.tvUpdatedDate.text = "Updated : ${Utils.convertDateFormatWithSpaceTime(order.updatedDate)?:"-"}"
            binding.tvSessionId.text = "Session ID : ${order.sessionId}"
            // Add more bindings as needed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        // Bind order data to the views
        holder.bind(order)
    }

    override fun getItemCount(): Int = orders.size
}