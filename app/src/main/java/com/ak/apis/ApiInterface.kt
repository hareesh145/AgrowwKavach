package com.ak.apis

import com.ak.model.ColdStorageResponse
import com.ak.model.DistrictResponse
import com.ak.model.LoginModel
import com.ak.model.LoginResponseModel
import com.ak.model.STLResponse
import com.ak.model.StateResponse
import com.ak.model.SuppliersResponse
import com.ak.model.UsersListResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("submit-user-login")
    suspend fun login(@Body loginModel: LoginModel): Response<LoginResponseModel>

    @POST("all-states")
    suspend fun getStates(@Body jsonObject: JsonObject): Response<StateResponse>

    @POST("all-districts-by-state")
    suspend fun getDistricts(@Body jsonObject: JsonObject): Response<DistrictResponse>


    @POST("users")
    suspend fun getUsers(@Body jsonObject: JsonObject): Response<UsersListResponse>


    @POST("cold-storages")
    suspend fun getColdStorages(@Body jsonObject: JsonObject): Response<ColdStorageResponse>

    @POST("soil-testings")
    suspend fun getSoilTestings(@Body jsonObject: JsonObject): Response<STLResponse>

    @POST("suppliers")
    suspend fun getSuppliers(@Body jsonObject: JsonObject): Response<SuppliersResponse>

}