package com.ak.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ak.databinding.ItemColdStorageBinding
import com.ak.model.ColdStoragesLocations

class ColdStorageAdapter(
    private val coldStorageList: List<ColdStoragesLocations>
) : RecyclerView.Adapter<ColdStorageAdapter.ColdStorageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColdStorageViewHolder {
        val binding =
            ItemColdStorageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ColdStorageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColdStorageViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return coldStorageList.size
    }

    inner class ColdStorageViewHolder(val binding: ItemColdStorageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val item = coldStorageList[adapterPosition]
            binding.coldStorageName.text = item.storageName
            binding.coldStorageLocation.text = item.storageAddress

            binding.root.setOnClickListener {
                println("Clicked")
            }
        }
    }
}