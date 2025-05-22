package com.ak.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ak.databinding.ColdStoragesBinding
import com.ak.ui.adapter.SoilTestAdapter
import com.ak.util.Result
import com.ak.util.Utils
import com.ak.viewmodel.SoilTestViewModel
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SoilTestFragment : Fragment() {
    lateinit var binding: ColdStoragesBinding
    val soilTestViewModel: SoilTestViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        ColdStoragesBinding.inflate(layoutInflater, container, false).apply {
            binding = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jsonObject = JsonObject().apply {
            addProperty("agrowwAreasId", 572)
        }

        soilTestViewModel.getSoilTestings(jsonObject)
        soilTestViewModel.soilTestResponse.observe(viewLifecycleOwner) {
            when(it){
                is Result.Loading -> {
                    Utils.showProgessBar(requireContext())
                }

                is Result.Success -> {
                    // Hide loading indicator
                    val response = it.data
                    binding.coldStorageRecyclerView.adapter =
                        response.soilTestingLaboratoriesList?.let { coldStorageList -> SoilTestAdapter(coldStorageList) }
                }

                is Result.Error -> {
                    // Hide loading indicator
                    // Handle error
                    Utils.hideProgressBar()
                }

                Result.Idle -> {
                    // Hide loading indicator
                    Utils.hideProgressBar()
                }
            }
        }


    }
}