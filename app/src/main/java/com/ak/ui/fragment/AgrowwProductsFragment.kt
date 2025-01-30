package com.ak.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ak.databinding.ProductsFragmentLayoutBinding
import com.ak.ui.home.HomeScreen
import com.google.gson.JsonObject

class AgrowwProductsFragment : Fragment() {

    lateinit var binding: ProductsFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ProductsFragmentLayoutBinding.inflate(inflater, container, false).also { binding = it }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}