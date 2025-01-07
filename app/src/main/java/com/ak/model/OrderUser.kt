package com.ak.model

data class OrderUser(
    val ecomOrdersGuestUserId: Int,
    val ecomOrdersId: Int,
    val email: String,
    val firstName: String,
    val lastName: String,
    val mobileNumber: String
)