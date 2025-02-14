package com.ak.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ak.R
import com.ak.databinding.ProductDetailsFragmentLayoutBinding
import com.ak.ui.adapter.ImagePagerAdapter
import com.ak.ui.adapter.ProductItemAdapter
import com.ak.ui.adapter.SimilarProductAdapter
import com.ak.ui.home.HomeScreen
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.google.gson.JsonObject

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
        (requireActivity() as HomeScreen).akViewModel.viewAgrowwItems(JsonObject().apply {
            addProperty("ecomAgrowwItemId", requireArguments().getInt("agrowwItemsId"))
        })

        (requireActivity() as HomeScreen).akViewModel.viewAgrowwItemsResponse.observe(
            requireActivity()
        ) {
            it?.let {
                println("Agroww Items: ${Gson().toJson(it)}")


//                binding.viewPager.adapter = AutoScrollPagerAdapter(requireContext(), it)
//                binding.indicator.setViewPager(binding.scrollViewPager)

                binding.productInfo = it.productInfo
                binding.viewPager.adapter = ImagePagerAdapter(
                    listOf(
                        R.drawable.image_two,
                        R.drawable.image_two,
                        R.drawable.image_two
                    )
                )

                // Attach TabLayout with ViewPager2
                TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()

                val variants = listOf("100g", "500g (Pack Of 100g)", "1kg")
                val chipGroup: ChipGroup = binding.chipGroup

                variants.forEach { variant ->
                    val chip = Chip(requireContext())
                    chip.text = variant
                    chip.isCheckable = true
                    chipGroup.addView(chip)
                }

                chipGroup.setOnCheckedChangeListener { group, checkedId ->
                    val selectedChip = group.findViewById<Chip>(checkedId)
                    Toast.makeText(
                        requireActivity(),
                        "Selected: ${selectedChip?.text}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                binding.recyclerRecommended.adapter =
                    ProductItemAdapter(it.recommendedProducts) { product ->
                        Toast.makeText(
                            requireActivity(),
                            "Clicked: ${product.shortDesc}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                binding.recyclerSimilar.layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                binding.recyclerSimilar.adapter =
                    SimilarProductAdapter(it.similarProducts) { product ->
                        Toast.makeText(
                            requireActivity(),
                            "Clicked: ${product.shortDesc}",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }

            }
        }
    }
}