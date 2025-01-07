package com.ak.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ak.databinding.WeatherForecastBinding

class WeatherForecastFragment : Fragment() {
    lateinit var binding: WeatherForecastBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        WeatherForecastBinding.inflate(layoutInflater, container, false).apply {
            binding = this
        }
        return binding.root
    }
}