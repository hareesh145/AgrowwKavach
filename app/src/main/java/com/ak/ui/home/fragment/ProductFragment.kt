package com.ak.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ak.R
import com.ak.databinding.ProductsFragmentLayoutBinding
import com.ak.ui.adapter.ProductItemAdapter
import com.ak.ui.home.HomeScreen
import com.ak.util.Utils
import com.google.gson.JsonObject

class ProductFragment : Fragment() {

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
        if (arguments != null) {
            val bundle = requireArguments()
            if (bundle.containsKey("ecomCategoriesId")) {
                val ecomCategoriesId = bundle.getInt("ecomCategoriesId")
                (requireActivity() as HomeScreen).akViewModel.getAgrowwItemsByCategory(JsonObject().apply {
                    addProperty("ecomCategoriesId", ecomCategoriesId)
                })
                (requireActivity() as HomeScreen).akViewModel.agrowwItemsByCategoryResponse.observe(
                    requireActivity()
                ) {
                    it?.let {
                        println("Agroww Items By Category: $it")
                        if (it.productsList.isNotEmpty()) {
                            binding.productsRv.adapter = ProductItemAdapter(it.productsList) {
                                // Handle item click
                                (requireActivity() as HomeScreen).navigateToNext(R.id.navigation_agroww_products,
                                    Bundle().apply {
                                        putInt("agrowwItemsId", it.ecomAgrowwItemId)
                                    })
                            }
                            binding.noProductsFoundTv.visibility = View.GONE
                        } else {
                            binding.noProductsFoundTv.visibility = View.VISIBLE
                        }
                    }
                }
            }

        }


    }
}