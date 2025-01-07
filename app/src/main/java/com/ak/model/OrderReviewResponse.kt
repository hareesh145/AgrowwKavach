package com.ak.model

data class OrderReviewResponse(
    val order: Order,
    val orderUser: OrderUser,
    val ordersCC: OrdersCC,
    val ordersShip: OrdersShip,
    val success: Boolean,
    val successCode: String,
    val userType: String
)