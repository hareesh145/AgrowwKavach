package com.ak.model

data class ShippingResponseModel(
    val shippingList: List<Shipping>,
    val success: Boolean,
    val successCode: String
)

data class Shipping(
    val addressLine1: String,
    val addressLine2: String,
    val city: String,
    val countryCode: String,
    val defaultFlag: String,
    val deleteDate: String,
    val deleteFlag: String,
    val name: String,
    val postalCode: String,
    val shippingId: Int,
    val state: String,
    val userId: Int
)