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
import com.ak.util.Utils
import com.bumptech.glide.Glide
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
        Utils.showProgessBar(requireContext())
        (requireActivity() as HomeScreen).akViewModel.viewAgrowwItems(JsonObject().apply {
            addProperty("ecomAgrowwItemId", requireArguments().getInt("agrowwItemsId"))
        })

        (requireActivity() as HomeScreen).akViewModel.viewAgrowwItemsResponse.observe(
            requireActivity()
        ) {
            Utils.hideProgressBar()
            it?.let {
                println("Agroww Items: ${Gson().toJson(it)}")


//                binding.viewPager.adapter = AutoScrollPagerAdapter(requireContext(), it)
//                binding.indicator.setViewPager(binding.scrollViewPager)

                binding.productInfo = it.productInfo
                Glide.with(binding.imageView)
                    .load("http://www.agrowwkavach.com:8080/AK_Images/products/${it.productInfo.imageURL}")
                    .into(binding.imageView)
//                binding.viewPager.adapter = ImagePagerAdapter(
//                    listOf(
//                        R.drawable.image_two,
//                        R.drawable.image_two,
//                        R.drawable.image_two
//                    )
//                )
//
//                // Attach TabLayout with ViewPager2
//                TabLayoutMediator(binding.tabLayout, binding.viewPager) { _, _ -> }.attach()

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

                if (!it.recommendedProducts.isNullOrEmpty()) {
                    binding.recyclerRecommended.visibility = View.VISIBLE
                    binding.recyclerRecommended.adapter =
                        ProductItemAdapter(it.recommendedProducts, { product ->
                            Toast.makeText(
                                requireActivity(),
                                "Clicked: ${product.shortDesc}",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }, {
                            Toast.makeText(
                                requireActivity(),
                                "Added to cart: ${it.shortDesc}",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }, { _, _ ->

                        })
                } else {
                    binding.recyclerRecommended.visibility = View.GONE
                }
                binding.recyclerSimilar.layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )

                if (!it.similarProducts.isNullOrEmpty()) {
                    binding.similarProductsCard.visibility = View.VISIBLE
                    binding.recyclerSimilar.adapter =
                        SimilarProductAdapter(it.similarProducts) { product ->
                            Toast.makeText(
                                requireActivity(),
                                "Clicked: ${product.shortDesc}",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                } else {
                    binding.similarProductsCard.visibility = View.GONE
                }

            }
        }
    }
}