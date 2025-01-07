package com.ak.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ak.databinding.ItemProductBinding

class ProductItemAdapter() : RecyclerView.Adapter<ProductItemAdapter.ProductItemVH>() {

    inner class ProductItemVH(binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemVH {
        return ProductItemVH(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {

        return 10
    }

    override fun onBindViewHolder(holder: ProductItemVH, position: Int) {

    }
}