package com.ak.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ak.databinding.ItemCategoriesBrandsBinding
import com.ak.model.Categories

class CategoriesAdapter(
    private val listOfHomeOptionModel: List<Categories>,
    private val onItemClick: (Categories) -> Unit
) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        ItemCategoriesBrandsBinding.inflate(
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

    inner class ViewHolder(val binding: ItemCategoriesBrandsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(categories: Categories) {
            binding.categories = categories
            itemView.setOnClickListener {
                onItemClick(listOfHomeOptionModel[adapterPosition])
            }
        }
    }
}