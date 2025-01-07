package com.ak.model

data class OrdersShip(
    val addressLine1: String,
    val addressLine2: String,
    val city: String,
    val countryCode: String,
    val ecomOrdersId: Int,
    val ecomOrdersShippingId: Int,
    val name: Any,
    val postalCode: String,
    val serviceType: Any,
    val shippingCost: Any,
    val state: String
)