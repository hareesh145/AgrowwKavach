package com.ak.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ak.databinding.ItemBrandsBinding
import com.ak.model.Brands

class BrandsAdapter(
    private val listOfHomeOptionModel: List<Brands>,
    private val onItemClick: (Brands) -> Unit
) :
    RecyclerView.Adapter<BrandsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        ItemBrandsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply {
            return ViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfHomeOptionModel[position])
    }

    override fun getItemCount(): Int {
        return listOfHomeOptionModel.size
    }

    inner class ViewHolder(val binding: ItemBrandsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(categories: Brands) {
            binding.categories = categories
            itemView.setOnClickListener {
                onItemClick(listOfHomeOptionModel[adapterPosition])
            }
        }
    }
}