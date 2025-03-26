package com.ak.ui.home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.ak.R
import com.ak.SharedPref
import com.ak.databinding.ProductsFragmentLayoutBinding
import com.ak.model.Products
import com.ak.ui.adapter.ProductItemAdapter
import com.ak.ui.home.HomeScreen
import com.ak.viewmodel.AKViewModel
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductFragment : Fragment() {

    lateinit var binding: ProductsFragmentLayoutBinding
//    val akViewModel: AKViewModel by activityViewModels()

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
                            binding.productsRv.adapter = ProductItemAdapter(it.productsList, {
                                // Handle item click
                                (requireActivity() as HomeScreen).navigateToNext(R.id.navigation_product_details,
                                    Bundle().apply {
                                        putInt("agrowwItemsId", it.ecomAgrowwItemId)
                                    })
                            }, { product: Products ->
                                // Handle add to cart
                                (requireActivity() as HomeScreen).akViewModel.addProductToCart(
                                    product,
                                    JsonObject().apply {
                                        addProperty("productId", product.ecomAgrowwItemId)
                                        addProperty("orderQty", 1)
                                        addProperty(
                                            "userId",
                                            SharedPref.getInstance(requireContext()).userProfile.userId
                                        )
                                    })
                            }, { product: Products, qty: Int ->
                                // Update Cart
                                (requireActivity() as HomeScreen).akViewModel.updateProductToCart(
                                    product, JsonObject().apply {
                                        addProperty("ecomOrdersId", product.ecomOrdersId)
                                        addProperty("price", qty * product.price)
                                        addProperty("orderQty", qty)
                                        addProperty(
                                            "userId",
                                            SharedPref.getInstance(requireContext()).userProfile.userId
                                        )
                                    })
                            })
                            binding.noProductsFoundTv.visibility = View.GONE

                            // ✅ Observe StateFlow using lifecycleScope
                            lifecycleScope.launch {
                                (requireActivity() as HomeScreen).akViewModel.cartUpdateFlow.collectLatest { update ->
                                    update?.let { productWitheCom ->
                                        Log.d("TAG", "onViewCreated: ${productWitheCom.second}")
                                        (binding.productsRv.adapter as ProductItemAdapter).onUpdateAddToCart(
                                            productWitheCom.first,
                                            productWitheCom.second.get("ecomOrdersId").asInt
                                        )
                                    }
                                }
                            }

                            // ✅ Observe StateFlow using lifecycleScope
                            lifecycleScope.launch {
                                (requireActivity() as HomeScreen).akViewModel.updatedCartFlow.collectLatest { update ->
                                    update?.let { productWitheCom ->
                                        Log.d("TAG", "onViewCreated: ${productWitheCom.second}")
//                                        (binding.productsRv.adapter as ProductItemAdapter).onUpdateAddToCart(
//                                            productWitheCom.first,
//                                            productWitheCom.second.get("ecomOrdersId").asInt
//                                        )
                                    }
                                }
                            }


                        } else {
                            binding.noProductsFoundTv.visibility = View.VISIBLE
                        }
                    }
                }
            }

        }


    }
}