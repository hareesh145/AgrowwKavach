package com.ak.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ak.domain.usecase.ShippingUseCase
import com.ak.model.ShippingResponseModel
import com.ak.util.Result
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShippingViewModel @Inject constructor(
    private val shippingUseCase: ShippingUseCase
): ViewModel()  {
    // ViewModel logic for Shipping can be added here
    // For example, you can create LiveData properties to hold the shipping data
    // and methods to fetch the shipping information using shippingUseCase.

    // Example:
    private val _shippingData = MutableLiveData<Result<ShippingResponseModel>>()
    val shippingData: LiveData<Result<ShippingResponseModel>> get() = _shippingData

    fun fetchShipping(request: JsonObject) {
        viewModelScope.launch {
            _shippingData.value = Result.Loading
            try {
                val response = shippingUseCase(request)
                _shippingData.value = response
            } catch (e: Exception) {
                _shippingData.value = Result.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}