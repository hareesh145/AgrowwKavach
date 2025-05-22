package com.ak.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ak.domain.usecase.SoilTestUseCase
import com.ak.model.STLResponse
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.ak.util.Result

@HiltViewModel
class SoilTestViewModel @Inject constructor(
    private val soilTestUseCase: SoilTestUseCase
) : ViewModel() {

    private val _soilTestResponse = MutableLiveData<Result<STLResponse>>()
    val soilTestResponse: LiveData<Result<STLResponse>> get() = _soilTestResponse

    fun getSoilTestings(soilTestRequest: JsonObject) {
        viewModelScope.launch {
            _soilTestResponse.value = Result.Loading
            try {
                val response = soilTestUseCase(soilTestRequest)
                _soilTestResponse.value = response
            } catch (e: Exception) {
                _soilTestResponse.value = Result.Error(e.localizedMessage ?: "Unknown error")
            } finally {
                _soilTestResponse.value = Result.Idle
            }
        }
    }
}