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
import com.ak.ui.adapter.HomeOptionsAdapter
import com.ak.ui.adapter.ProductListAdapter
import com.ak.ui.home.HomeScreen

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
                    }

                    "Workshop" -> {
                        // Navigate to Workshop Screen
                    }
                }
            }

        binding.productsList.adapter = ProductListAdapter(productsList)


    }
}