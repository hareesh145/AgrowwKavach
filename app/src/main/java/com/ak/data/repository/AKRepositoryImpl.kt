package com.ak.data.repository

import com.ak.data.remote.ApiInterface
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
import com.ak.model.ShippingResponseModel
import com.ak.model.StateResponse
import com.ak.util.Result
import com.ak.util.safeApiCall
import com.google.gson.JsonObject
import javax.inject.Inject

class AKRepositoryImpl @Inject constructor(
    private val api: ApiInterface
) : AKRepository {
    override suspend fun login(loginModel: LoginModel): Result<LoginResponseModel> {
        return safeApiCall {
            val response = api.login(loginModel)
            if (response.isSuccessful) {
                response.body() ?: LoginResponseModel()
            } else {
                throw Exception("Failed to load login data")
            }
        }
    }

    override suspend fun getAllStates(jsonObject: JsonObject): Result<StateResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getDistricts(jsonObject: JsonObject): Result<DistrictResponse> {
        return safeApiCall {
            val response = api.getDistricts(jsonObject)
            if (response.isSuccessful) {
                response.body()?: DistrictResponse()
            } else {
                throw Exception("Failed to load districts")
            }
        }
    }

    override suspend fun getColdStorages(jsonObject: JsonObject): Result<ColdStorageResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getSoilTestings(jsonObject: JsonObject): Result<STLResponse> {
        return safeApiCall {
            val response = api.getSoilTestings(jsonObject)
            if (response.isSuccessful) {
                response.body() ?: STLResponse()
            } else {
                throw Exception("Failed to load soil testing data")
            }
        }
    }

    override suspend fun getCategories(jsonObject: JsonObject): Result<CategoriesResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getBrands(jsonObject: JsonObject): Result<BrandsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getAgrowwItemsByCategory(jsonObject: JsonObject): Result<ProductsListResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun getAgrowwItemsByBrand(jsonObject: JsonObject): Result<ProductsListResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun viewAgrowwItems(jsonObject: JsonObject): Result<AgrowwProductsResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun addProductToCart(jsonObject: JsonObject): Result<JsonObject> {
        TODO("Not yet implemented")
    }

    override suspend fun updateProductToCart(jsonObject: JsonObject): Result<JsonObject> {
        TODO("Not yet implemented")
    }

    override suspend fun getCart(jsonObject: JsonObject): Result<JsonObject> {
        TODO("Not yet implemented")
    }

    override suspend fun getMyOrders(jsonObject: JsonObject): Result<MyOrdersResponse> {
        return safeApiCall {
            val response = api.getOrders(jsonObject)
            if (response.isSuccessful) {
                response.body() ?: MyOrdersResponse()
            } else {
                throw Exception("Failed to load my orders")
            }
        }
    }

    override suspend fun getShippings(jsonObject: JsonObject): Result<ShippingResponseModel> {
        return safeApiCall {
            val response = api.getShippings(jsonObject)
            if (response.isSuccessful) {
                response.body() ?: ShippingResponseModel()
            } else {
                throw Exception("Failed to load shipping data")
            }
        }
    }
}