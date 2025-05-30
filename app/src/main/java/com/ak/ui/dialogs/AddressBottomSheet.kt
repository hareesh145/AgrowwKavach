package com.ak.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ak.R
import com.ak.SharedPref
import com.ak.databinding.NewAddressLayoutBinding
import com.ak.model.AddShippingModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AddressBottomSheet() : BottomSheetDialogFragment() {
    override fun getTheme(): Int = R.style.TransparentBottomSheetDialog
    lateinit var binding: NewAddressLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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

        binding.saveAddressCard.setOnClickListener {
            // Handle save address logic here
            // You can access the input fields using binding.<field_name>
            // For example: val address = binding.addressInput.text.toString()
            val addShippingModel = AddShippingModel(
                userId = SharedPref.getInstance(requireContext()).userProfile.userId, // Replace with actual user ID
                name = binding.name.text.toString(),
                addressLine1 = binding.officeBuilding.text.toString(),
                addressLine2 = binding.addressLine2.text.toString(),
                city = binding.cityTxt.text.toString(),
                state = binding.state.text.toString(),
                countryCode = binding.country.text.toString(),
                postalCode = binding.postalCode.text.toString(),
                defaultFlag = if (binding.isDefaultSwitch.isChecked) "Y" else "N"
            )
            dismiss()
        }
    }
}