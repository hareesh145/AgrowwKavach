package com.ak.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ak.model.LoginModel
import com.ak.model.LoginResponseModel
import kotlinx.coroutines.launch

class LoginViewModel(val repository: NetworkRepository) : ViewModel() {

    val loginResponse = MutableLiveData<LoginResponseModel>()
    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()

    fun login(loginModel: LoginModel) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val response = repository.login(loginModel)
                if (response.isSuccessful) {
                    loginResponse.postValue(response.body())
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
