package com.ak.viewmodel

import com.ak.apis.ApiInterface
import com.ak.apis.RetrofitClient
import com.ak.model.LoginModel
import com.google.gson.JsonObject

class NetworkRepository {

    private val retrofitInstance = RetrofitClient.getInstance().create(ApiInterface::class.java)

    suspend fun login(loginModel: LoginModel) = retrofitInstance.login(loginModel)

    suspend fun getAllStates(jsonObject: JsonObject) = retrofitInstance.getStates(jsonObject)

    suspend fun getDistricts(jsonObject: JsonObject) = retrofitInstance.getDistricts(jsonObject)

    suspend fun getColdStorages(jsonObject: JsonObject) =
        retrofitInstance.getColdStorages(jsonObject)

    suspend fun getCategories(jsonObject: JsonObject) = retrofitInstance.getCategories(jsonObject)

    suspend fun getBrands(jsonObject: JsonObject) = retrofitInstance.getBrands(jsonObject)

    suspend fun getAgrowwItemsByCategory(jsonObject: JsonObject) =
        retrofitInstance.getAgrowwItemsByCategory(jsonObject)

    suspend fun getAgrowwItemsByBrand(jsonObject: JsonObject) =
        retrofitInstance.getAgrowwItemsByBrand(jsonObject)
    suspend fun viewAgrowwItems(jsonObject: JsonObject) = retrofitInstance.viewAgrowwItems(jsonObject)

    suspend fun addProductToCart(jsonObject: JsonObject) = retrofitInstance.addProductToCart(jsonObject)

    suspend fun updateProductToCart(jsonObject: JsonObject) = retrofitInstance.updateProductToCart(jsonObject)

    suspend fun getCart(jsonObject: JsonObject) = retrofitInstance.getCart(jsonObject)
}
