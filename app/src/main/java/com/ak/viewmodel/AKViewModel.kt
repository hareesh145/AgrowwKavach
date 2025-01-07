package com.ak.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ak.model.ColdStorageResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class AKViewModel(private val repository: NetworkRepository) : ViewModel() {
    val coldStorageResponse = MutableLiveData<ColdStorageResponse>()

    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()


    fun getColdStorages(loginModel: JsonObject) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val response = repository.getColdStorages(loginModel)
                if (response.isSuccessful) {
                    coldStorageResponse.postValue(response.body())
                } else {
                    errorMessage.postValue("Error: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                errorMessage.postValue(e.localizedMessage ?: "Unknown error")
            } finally {
                isLoading.postValue(false)
            }
        }
    }


}