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


}