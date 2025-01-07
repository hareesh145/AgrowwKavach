package com.ak.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ak.databinding.NavItemBinding
import com.ak.model.NavItem

class NavAdapter(
    private val items: List<NavItem>,
    private val onClick: (NavItem) -> Unit
) : RecyclerView.Adapter<NavAdapter.NavViewHolder>() {

    inner class NavViewHolder(private val binding: NavItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: NavItem) {
            binding.navItemIcon.setImageResource(item.icon)
            binding.navItemTitle.text = item.title
            itemView.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavViewHolder {
        val view = NavItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NavViewHolder(view)
    }

    override fun onBindViewHolder(holder: NavViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}