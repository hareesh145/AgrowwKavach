package com.ak.model

data class AddShippingModel(
    val addressLine1: String,
    val addressLine2: String,
    val city: String,
    val countryCode: String,
    val defaultFlag: String,
    val name: String,
    val postalCode: String,
    val state: String,
    val userId: Int
)