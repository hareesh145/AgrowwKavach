package com.ak.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ak.databinding.ItemHomeOptionsBinding
import com.ak.model.HomeOptionModel
import com.ak.ui.home.fragment.WeatherForecastFragment

class HomeOptionsAdapter(
    val activity: AppCompatActivity,
    private val listOfHomeOptionModel: List<HomeOptionModel>,
    val listener: (HomeOptionModel) -> Unit
) :
    RecyclerView.Adapter<HomeOptionsAdapter.HomeOptionsHolder>() {

    inner class HomeOptionsHolder(private val binding: ItemHomeOptionsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                listener.invoke(listOfHomeOptionModel[adapterPosition])
            }
        }

        fun bind(homeOptionModel: HomeOptionModel) {
            binding.homeOptionModel = homeOptionModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeOptionsHolder {
        return HomeOptionsHolder(
            ItemHomeOptionsBinding.inflate(
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