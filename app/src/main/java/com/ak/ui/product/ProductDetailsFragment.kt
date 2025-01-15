package com.ak.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ak.databinding.ProductDetailsFragmentLayoutBinding

class ProductDetailsFragment : Fragment() {
    lateinit var binding: ProductDetailsFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ProductDetailsFragmentLayoutBinding.inflate(inflater, container, false)
            .also { binding = it }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}