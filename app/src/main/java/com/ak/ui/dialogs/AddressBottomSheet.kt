package com.ak.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ak.databinding.NewAddressLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AddressBottomSheet(): BottomSheetDialogFragment() {

    lateinit var binding : NewAddressLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewAddressLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize views and set up listeners here
        binding.closeMark.setOnClickListener {
            dismiss()
        }
        // Add more initialization code as needed
    }
}