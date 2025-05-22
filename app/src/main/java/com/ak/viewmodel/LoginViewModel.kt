package com.ak.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ak.domain.usecase.LoginUseCase
import com.ak.model.LoginModel
import com.ak.model.LoginResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.ak.util.Result

@HiltViewModel
class LoginViewModel @Inject constructor(val loginUseCase: LoginUseCase) : ViewModel() {
    private val _loginResponse = MutableLiveData<Result<LoginResponseModel>>()
    val loginResponse: MutableLiveData<Result<LoginResponseModel>> get() = _loginResponse

    fun login(loginModel: LoginModel) {
        viewModelScope.launch {
            _loginResponse.value = Result.Loading
            try {
                val response = loginUseCase(loginModel)
                _loginResponse.value = response
            } catch (e: Exception) {
                _loginResponse.value= Result.Error(e.localizedMessage ?: "Unknown error")
            } finally {
                _loginResponse.value = Result.Idle
            }
        }
    }
}
