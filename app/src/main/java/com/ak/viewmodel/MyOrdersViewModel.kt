package com.ak.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ak.domain.usecase.MyOrdersUseCase
import com.ak.model.MyOrdersResponse
import com.ak.util.Result
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyOrdersViewModel @Inject constructor(
    private val myOrdersUseCase: MyOrdersUseCase
) : ViewModel() {

    // ViewModel logic for MyOrders can be added here
    // For example, you can create LiveData properties to hold the orders data
    // and methods to fetch the orders using myOrdersUseCase.

    // Example:
     private val _orders = MutableLiveData<Result<MyOrdersResponse>>()
     val orders: LiveData<Result<MyOrdersResponse>> get() = _orders

     fun fetchMyOrders(request: JsonObject) {
         viewModelScope.launch {
             _orders.value = Result.Loading
             try {
                 val response = myOrdersUseCase(request)
                 _orders.value = response
             } catch (e: Exception) {
                 _orders.value = Result.Error(e.localizedMessage ?: "Unknown error")
             }
         }
     }
}