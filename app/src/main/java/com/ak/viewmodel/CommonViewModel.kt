package com.ak.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ak.model.DistrictResponse
import com.ak.model.LoginModel
import com.ak.model.StateResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class CommonViewModel(val repository: NetworkRepository) : ViewModel() {
    val stateResponse = MutableLiveData<StateResponse>()
    val districtResponse = MutableLiveData<DistrictResponse>()

    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()


    fun getAllStates(loginModel: JsonObject) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val response = repository.getAllStates(loginModel)
                if (response.isSuccessful) {
                    stateResponse.postValue(response.body())
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

    fun getDistricts(jsonObject: JsonObject) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val response = repository.getDistricts(jsonObject)
                if (response.isSuccessful) {
                    districtResponse.postValue(response.body())
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