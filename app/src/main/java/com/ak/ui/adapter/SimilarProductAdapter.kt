package com.ak.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ak.databinding.ItemProductBinding
import com.ak.databinding.ItemSimilarProductsLayoutBinding
import com.ak.model.Products

class SimilarProductAdapter(
    val productList: List<Products>,
    val onItemClick: (product: Products) -> Unit
) : RecyclerView.Adapter<SimilarProductAdapter.ProductItemVH>() {

    inner class ProductItemVH(val binding: ItemSimilarProductsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Products) {
            // binding.productName.text = product.productName
            // binding.productPrice.text = product.productPrice
            // binding.productImage.load(product.productImage)
            binding.product = product
        }

        init {
            binding.root.setOnClickListener {
                onItemClick(productList[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemVH {
        return ProductItemVH(
            ItemSimilarProductsLayoutBinding.inflate(
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