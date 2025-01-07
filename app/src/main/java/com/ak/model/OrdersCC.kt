package com.ak.model

data class OrdersCC(
    val addressLine1: String,
    val addressLine2: String,
    val applicationProfileId: Any,
    val city: String,
    val countryCode: String,
    val creditCardNum: String,
    val creditCardType: String,
    val creditCardVerificationNum: Int,
    val ecomOrdersCreditCardId: Int,
    val ecomOrdersId: Int,
    val expirationMonth: Int,
    val expirationYear: Int,
    val merchantProfileId: Any,
    val postalCode: String,
    val serviceId: Any,
    val state: String,
    val transactionId: Any
)