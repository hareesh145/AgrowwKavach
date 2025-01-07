package com.ak.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ak.databinding.ItemCategoriesBinding
import com.ak.databinding.ItemHomeOptionsBinding
import com.ak.model.HomeOptionModel

class ProductListAdapter(private val listOfHomeOptionModel: List<HomeOptionModel>) :
    RecyclerView.Adapter<ProductListAdapter.HomeOptionsHolder>() {

    inner class HomeOptionsHolder(val binding: ItemCategoriesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(homeOptionModel: HomeOptionModel) {
            binding.homeOptionModel = homeOptionModel
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeOptionsHolder {
        return HomeOptionsHolder(
            ItemCategoriesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listOfHomeOptionModel.size
    }

    override fun onBindViewHolder(holder: HomeOptionsHolder, position: Int) {
        holder.bind(listOfHomeOptionModel[position])
    }
}