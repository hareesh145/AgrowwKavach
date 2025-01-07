package com.ak.model

data class OrdersResponse(
    val success: Boolean,
    val successCode: String,
    val userOrders: List<UserOrder>
)