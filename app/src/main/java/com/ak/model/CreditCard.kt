package com.ak.model

data class CreditCard(
    val addressLine1: String,
    val addressLine2: String,
    val city: String,
    val countryCode: String,
    val creditCardId: Int,
    val creditCardNum: String,
    val creditCardType: String,
    val creditCardVerificationNum: Int,
    val defaultFlag: String,
    val deleteDate: String,
    val deleteFlag: String,
    val expirationMonth: Int,
    val expirationYear: Int,
    val name: String,
    val postalCode: String,
    val state: String,
    val userId: Int
)