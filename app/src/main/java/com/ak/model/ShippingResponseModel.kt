package com.ak.model

data class ShippingResponseModel(
    val shippingList: List<Shipping>? = null,
    val success: Boolean? = null,
    val successCode: String? = null,
)

data class Shipping(
    val addressLine1: String?= null,
    val addressLine2: String?= null,
    val city: String?= null,
    val countryCode: String?= null,
    val defaultFlag: String?= null,
    val deleteDate: String?= null,
    val deleteFlag: String?= null,
    val name: String?= null,
    val postalCode: String?= null,
    val shippingId: Int? = null,
    val state: String?= null,
    val userId: Int? = null,
)