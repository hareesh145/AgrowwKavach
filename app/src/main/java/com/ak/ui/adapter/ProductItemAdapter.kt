package com.ak.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ak.R
import com.ak.databinding.ItemProductBinding
import com.ak.model.Products
import com.bumptech.glide.Glide
import com.google.gson.JsonObject

class ProductItemAdapter(
    val productList: List<Products>,
    val onItemClick: (product: Products) -> Unit,
    val onAddToCart: (product: Products) -> Unit,
    val onUpdateCart: (product: Products, qty: Int) -> Unit
) : RecyclerView.Adapter<ProductItemAdapter.ProductItemVH>() {

    inner class ProductItemVH(val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Products) {
            // binding.productName.text = product.productName
            // binding.productPrice.text = product.productPrice
            binding.product = product
            binding.addToCartBtn.visibility = if (product.ecomOrdersId != null) {
                binding.increaseDecreaseView.visibility = View.VISIBLE
                View.GONE
            } else {
                binding.increaseDecreaseView.visibility = View.GONE
                View.VISIBLE
            }
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
            binding.addToCartBtn.setOnClickListener {
                onAddToCart(productList[adapterPosition])
            }
            binding.increaseView.setOnClickListener {
                val quantity = binding.quantityTv.text.toString().toInt()
                binding.quantityTv.text = "${(quantity.plus(1))}"
                onUpdateCart(productList[adapterPosition], quantity.plus(1))
            }
            binding.decreseView.setOnClickListener {
                val quantity = binding.quantityTv.text.toString().toInt()
                if (quantity > 1) {
                    binding.quantityTv.text = "${(quantity.minus(1))}"
                    onUpdateCart(productList[adapterPosition], quantity.minus(1))
                } else {
                    binding.addToCartBtn.visibility = View.VISIBLE
                    binding.increaseDecreaseView.visibility = View.GONE
                }
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

    fun onUpdateAddToCart(product: Products, ecomOrderId: Int) {
        val index = productList.indexOfFirst { product.ecomAgrowwItemId == it.ecomAgrowwItemId }
        if (index != -1) {
            productList[index].ecomOrdersId = ecomOrderId
            notifyItemChanged(index)  // Refresh only this item
        }
    }
}