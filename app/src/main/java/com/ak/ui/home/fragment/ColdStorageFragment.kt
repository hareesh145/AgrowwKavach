package com.ak.ui.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ak.databinding.ColdStoragesBinding
import com.ak.ui.adapter.ColdStorageAdapter
import com.ak.util.Utils
import com.ak.viewmodel.AKViewModel
import com.ak.viewmodel.NetworkRepository
import com.google.gson.JsonObject

class ColdStorageFragment : Fragment() {
    val TAG = ColdStorageFragment::class.java.simpleName
    lateinit var binding: ColdStoragesBinding
    val akViewModel = AKViewModel(NetworkRepository())


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
            addProperty("agrowwDistrictsId", 572)
        }

        akViewModel.getColdStorages(jsonObject)
        akViewModel.coldStorageResponse.observe(requireActivity()) {
            it?.let {
                println("Cold Storage Response: $it")
                binding.coldStorageRecyclerView.adapter =
                    ColdStorageAdapter(it.coldStoragesLocationsList)
            }
        }

        akViewModel.isLoading.observe(requireActivity()) {
            println("Loading: $it")
            if (it) {
                Utils.showProgessBar(requireActivity())
            } else {
                Utils.hideProgressBar()
            }
        }
    }


}
