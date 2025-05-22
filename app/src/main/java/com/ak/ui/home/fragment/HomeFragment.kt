package com.ak.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ak.R
import com.ak.databinding.HomeFragmentBinding
import com.ak.model.HomeOptionModel
import com.ak.ui.adapter.BrandsAdapter
import com.ak.ui.adapter.CategoriesAdapter
import com.ak.ui.adapter.HomeOptionsAdapter
import com.ak.ui.home.HomeScreen
import com.ak.util.Utils
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val homeOptions = arrayListOf<HomeOptionModel>()

    private val productsList = arrayListOf<HomeOptionModel>()
    lateinit var binding: HomeFragmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeOptions.apply {
            add(HomeOptionModel(R.drawable.ic_weather, "Weather Forecast"))
            add(HomeOptionModel(R.drawable.ic_cold_storage, "Cold Storage"))
            add(HomeOptionModel(R.drawable.ic_soil_testing, "Soil Testing"))
            add(HomeOptionModel(R.drawable.ic_workshop, "Workshop"))
        }
        productsList.apply {
            add(HomeOptionModel(R.drawable.image_one, "Implements"))
            add(HomeOptionModel(R.drawable.image_two, "Traps and lures"))
            add(HomeOptionModel(R.drawable.image_three, "Clawmat"))
            add(HomeOptionModel(R.drawable.image_four, "Vermi Compost"))
            add(HomeOptionModel(R.drawable.image_five, "Coco Peat"))
            add(HomeOptionModel(R.drawable.image_six, "Spices"))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHomeOptions()
    }

    private fun setHomeOptions() {
        binding.categories.adapter =
            HomeOptionsAdapter(requireActivity() as AppCompatActivity, homeOptions) {
                when (it.optionText) {
                    "Weather Forecast" -> {
                        // Navigate to Weather Forecast Screen
                        (requireActivity() as HomeScreen).navigateToNext(R.id.navigation_weather)
                    }

                    "Cold Storage" -> {
                        (requireActivity() as HomeScreen).navigateToNext(R.id.navigation_cold_storage)
                    }

                    "Soil Testing" -> {
                        // Navigate to Soil Testing Screen
                        (requireActivity() as HomeScreen).navigateToNext(R.id.navigation_soil_testing)
                    }

                    "Workshop" -> {
                        // Navigate to Workshop Screen
                    }
                }
            }

        getCategories()
        binding.productCategories.setOnClickListener {
            getCategories()
        }

        binding.brandsSection.setOnClickListener {
            getBrands()
        }


    }

    private fun getBrands() {
        Utils.showProgessBar(requireActivity())
        (requireActivity() as HomeScreen).akViewModel.getBrands(JsonObject().apply {
            addProperty("deleteFlag", "N")
        })
        (requireActivity() as HomeScreen).akViewModel.brandsResponse.observe(viewLifecycleOwner) {
            Utils.hideProgressBar()
            if (it.success) {
                // Update UI
                binding.productsList.adapter = BrandsAdapter(it.brandsList) {
                    (requireActivity() as HomeScreen).navigateToNext(
                        R.id.navigation_products,
                        Bundle().apply {
                            putInt("ecomBrandsId", it.brandId)
                        })
                }

            } else {
                // Show error message
            }
        }
    }

    private fun getCategories() {
        Utils.showProgessBar(requireActivity())
        (requireActivity() as HomeScreen).akViewModel.getCategories(JsonObject().apply {
            addProperty("deleteFlag", "N")
        })
        (requireActivity() as HomeScreen).akViewModel.categoriesResponse.observe(viewLifecycleOwner) {
            Utils.hideProgressBar()
            if (it.success) {
                // Update UI
                binding.productsList.adapter = CategoriesAdapter(it.categoriesList) {
                    (requireActivity() as HomeScreen).navigateToNext(
                        R.id.navigation_products,
                        Bundle().apply {
                            putInt("ecomCategoriesId", it.ecomCategoriesId)
                        })
                }

            } else {
                // Show error message
            }
        }
    }
}