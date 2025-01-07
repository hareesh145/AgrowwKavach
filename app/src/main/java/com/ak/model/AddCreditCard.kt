package com.ak.model

data class AddCreditCard(
    val addressLine1: String,
    val addressLine2: String,
    val city: String,
    val countryCode: String,
    val creditCardNum: String,
    val creditCardType: String,
    val creditCardVerificationNum: String,
    val defaultFlag: String,
    val expirationMonth: String,
    val expirationYear: String,
    val name: String,
    val postalCode: String,
    val state: String,
    val userId: Int
)