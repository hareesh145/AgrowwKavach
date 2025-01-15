package com.ak.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ak.model.AgrowwProductsResponse
import com.ak.model.BrandsResponse
import com.ak.model.CategoriesResponse
import com.ak.model.ColdStorageResponse
import com.ak.model.ProductsListResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class AKViewModel(private val repository: NetworkRepository) : ViewModel() {
    val agrowwItemsByCategoryResponse = MutableLiveData<ProductsListResponse>()
    val agrowwItemsByBrandResponse = MutableLiveData<ProductsListResponse>()
    val viewAgrowwItemsResponse = MutableLiveData<AgrowwProductsResponse>()
    val coldStorageResponse = MutableLiveData<ColdStorageResponse>()
    val categoriesResponse = MutableLiveData<CategoriesResponse>()
    val brandsResponse = MutableLiveData<BrandsResponse>()

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

    fun getCategories(loginModel: JsonObject) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val response = repository.getCategories(loginModel)
                if (response.isSuccessful) {
                    categoriesResponse.postValue(response.body())
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

    fun getBrands(loginModel: JsonObject) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val response = repository.getBrands(loginModel)
                if (response.isSuccessful) {
                    brandsResponse.postValue(response.body())
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


    fun getAgrowwItemsByCategory(loginModel: JsonObject) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val response = repository.getAgrowwItemsByCategory(loginModel)
                if (response.isSuccessful) {
                    agrowwItemsByCategoryResponse.postValue(response.body())
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

    fun viewAgrowwItems(loginModel: JsonObject) {
        viewModelScope.launch {
            isLoading.postValue(true)
            try {
                val response = repository.viewAgrowwItems(loginModel)
                if (response.isSuccessful) {
                    viewAgrowwItemsResponse.postValue(response.body())
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