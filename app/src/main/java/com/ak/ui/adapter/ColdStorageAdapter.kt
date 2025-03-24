package com.ak.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ak.R
import com.ak.databinding.ItemColdStorageBinding
import com.ak.model.ColdStoragesLocations
import com.bumptech.glide.Glide

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
//
            Glide.with(binding.root.context).load(
                "http://www.agrowwkavach.com:8080/AK_Images/gubba/${item.imageName}"
            ).placeholder(R.drawable.ic_placeholder).into(
                binding.coldStorageImage
            )
            binding.root.setOnClickListener {
                println("Clicked")
            }
        }
    }
}