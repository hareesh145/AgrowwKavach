package com.ak.model

data class CheckoutModel(
    val addressLine1: String,
    val addressLine2: String,
    val city: String,
    val countryCode: String,
    val creditCardNum: String,
    val creditCardType: String,
    val creditCardVerificationNum: String,
    val ecomOrdersId: Int,
    val expirationMonth: String,
    val expirationYear: String,
    val postalCode: String,
    val state: String
)