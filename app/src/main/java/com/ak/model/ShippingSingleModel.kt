package com.ak.model

data class ShippingSingleModel(
    val success: Boolean,
    val successCode: String,
    val shipping: Shipping
)