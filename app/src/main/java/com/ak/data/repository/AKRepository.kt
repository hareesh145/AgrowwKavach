package com.ak.data.repository

import com.ak.model.AgrowwProductsResponse
import com.ak.model.BrandsResponse
import com.ak.model.CategoriesResponse
import com.ak.model.ColdStorageResponse
import com.ak.model.DistrictResponse
import com.ak.model.LoginModel
import com.ak.model.LoginResponseModel
import com.ak.model.MyOrdersResponse
import com.ak.model.ProductsListResponse
import com.ak.model.STLResponse
import com.ak.model.StateResponse
import com.ak.util.Result
import com.google.gson.JsonObject

interface AKRepository {
    suspend fun login(loginModel: LoginModel): Result<LoginResponseModel>
    suspend fun getAllStates(jsonObject: JsonObject): Result<StateResponse>
    suspend fun getDistricts(jsonObject: JsonObject): Result<DistrictResponse>
    suspend fun getColdStorages(jsonObject: JsonObject): Result<ColdStorageResponse>
    suspend fun getSoilTestings(jsonObject: JsonObject): Result<STLResponse>
    suspend fun getCategories(jsonObject: JsonObject): Result<CategoriesResponse>
    suspend fun getBrands(jsonObject: JsonObject): Result<BrandsResponse>
    suspend fun getAgrowwItemsByCategory(jsonObject: JsonObject): Result<ProductsListResponse>
    suspend fun getAgrowwItemsByBrand(jsonObject: JsonObject): Result<ProductsListResponse>
    suspend fun viewAgrowwItems(jsonObject: JsonObject): Result<AgrowwProductsResponse>
    suspend fun addProductToCart(jsonObject: JsonObject): Result<JsonObject>
    suspend fun updateProductToCart(jsonObject: JsonObject): Result<JsonObject>
    suspend fun getCart(jsonObject: JsonObject): Result<JsonObject>

    //My Account
    suspend fun getMyOrders(jsonObject: JsonObject): Result<MyOrdersResponse>
}