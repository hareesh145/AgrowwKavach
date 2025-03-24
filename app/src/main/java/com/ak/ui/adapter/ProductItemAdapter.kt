package com.ak.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ak.R
import com.ak.databinding.ItemProductBinding
import com.ak.model.Products
import com.bumptech.glide.Glide

class ProductItemAdapter(
    val productList: List<Products>,
    val onItemClick: (product: Products) -> Unit
) : RecyclerView.Adapter<ProductItemAdapter.ProductItemVH>() {

    inner class ProductItemVH(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Products) {
            // binding.productName.text = product.productName
            // binding.productPrice.text = product.productPrice
            binding.product = product
            Glide.with(binding.root.context).load(
                "http://www.agrowwkavach.com:8080/AK_Images/products/${product.imageURL}"
            ).placeholder(R.drawable.ic_placeholder).into(
                binding.medicineImg
            )
        }

        init {
            binding.root.setOnClickListener {
                onItemClick(productList[adapterPosition])
            }
        }
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
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductItemVH, position: Int) {
        holder.bind(productList[position])
    }
}