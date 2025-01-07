package com.ak.apis

import com.ak.model.AddColdStorage
import com.ak.model.AddCreditCard
import com.ak.model.AddShippingModel
import com.ak.model.AddSoilTest
import com.ak.model.AddSupplierModel
import com.ak.model.BrandsResponse
import com.ak.model.CategoriesResponse
import com.ak.model.ColdStorageResponse
import com.ak.model.CreditCardListResponse
import com.ak.model.DistrictResponse
import com.ak.model.LoginModel
import com.ak.model.LoginResponseModel
import com.ak.model.OrderReviewResponse
import com.ak.model.STLResponse
import com.ak.model.ShippingResponseModel
import com.ak.model.ShippingSingleModel
import com.ak.model.StateResponse
import com.ak.model.SuppliersResponse
import com.ak.model.UserInfoResponse
import com.ak.model.UsersListResponse
import com.ak.model.ViewUserCreditCardModel
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

    @POST("view-users-info")
    suspend fun viewUsersInfo(@Body jsonObject: JsonObject): Response<UserInfoResponse>

    @POST("delete-users")
    suspend fun deleteUsers(@Body jsonObject: JsonObject): Response<JsonObject>

    @POST("add-cold-storages")
    suspend fun addColdStorages(@Body jsonObject: AddColdStorage): Response<JsonObject>

    @POST("update-cold-storages")
    suspend fun updateColdStorages(@Body jsonObject: AddColdStorage): Response<JsonObject>

    @POST("add-soil-testings")
    suspend fun addSoilTestings(@Body jsonObject: AddSoilTest): Response<JsonObject>

    @POST("add-supplier")
    suspend fun addSupplier(@Body jsonObject: AddSupplierModel): Response<JsonObject>

    @POST("update-supplier")
    suspend fun updateSupplier(@Body jsonObject: AddSupplierModel): Response<JsonObject>

    @POST("shippings")
    suspend fun getShippings(@Body jsonObject: JsonObject): Response<ShippingResponseModel>

    @POST("add-shipping")
    suspend fun addShipping(@Body jsonObject: AddShippingModel): Response<JsonObject>

    @POST("update-shipping")
    suspend fun updateShipping(@Body jsonObject: AddShippingModel): Response<JsonObject>

    @POST("add-default-shipping")
    suspend fun addDefaultShipping(@Body jsonObject: AddShippingModel): Response<JsonObject>

    @POST("view-user-shipping")
    suspend fun viewUserShipping(@Body jsonObject: JsonObject): Response<ShippingSingleModel>

    @POST("view-default-shipping")
    suspend fun viewDefaultShipping(@Body jsonObject: JsonObject): Response<ShippingSingleModel>

    @POST("creditcards")
    suspend fun getCreditCards(@Body jsonObject: JsonObject): Response<CreditCardListResponse>

    @POST("add-creditcard")
    suspend fun addCreditCard(@Body jsonObject: AddCreditCard): Response<JsonObject>

    @POST("update-creditcard")
    suspend fun updateCreditCard(@Body jsonObject: AddCreditCard): Response<JsonObject>

    @POST("add-default-creditcard")
    suspend fun addDefaultCreditCard(@Body jsonObject: AddCreditCard): Response<JsonObject>

    @POST("view-user-creditcard")
    suspend fun viewUserCreditCard(@Body jsonObject: JsonObject): Response<ViewUserCreditCardModel>

    @POST("ecom-brands")
    suspend fun getBrands(@Body jsonObject: JsonObject): Response<BrandsResponse>

    @POST("add-ecom-brands")
    suspend fun addBrands(@Body jsonObject: JsonObject): Response<JsonObject>

    @POST("ecom-categories")
    suspend fun getCategories(@Body jsonObject: JsonObject): Response<CategoriesResponse>

    @POST("add-product-to-cart")
    suspend fun addProductToCart(@Body jsonObject: JsonObject): Response<JsonObject>

    @POST("update-product-to-cart")
    suspend fun updateProductToCart(@Body jsonObject: JsonObject): Response<JsonObject>

    @POST("delete-product-from-cart")
    suspend fun deleteProductFromCart(@Body jsonObject: JsonObject): Response<JsonObject>

    @POST("checkout-guest-user")
    suspend fun checkoutGuestUser(@Body jsonObject: JsonObject): Response<JsonObject>

    @POST("checkout-shipping")
    suspend fun checkoutShipping(@Body jsonObject: JsonObject): Response<JsonObject>

    @POST("checkout-payment")
    suspend fun checkoutPayment(@Body jsonObject: JsonObject): Response<JsonObject>

    @POST("order-review")
    suspend fun orderReview(@Body jsonObject: JsonObject): Response<OrderReviewResponse>

    @POST("orders")
    suspend fun getOrders(@Body jsonObject: JsonObject): Response<JsonObject>

    @POST("orders-checkout-status")
    suspend fun getOrdersCheckoutStatus(@Body jsonObject: JsonObject): Response<JsonObject>

}