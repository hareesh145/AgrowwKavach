package com.ak.model

data class Order(
    val checkoutFlag: String,
    val createdBy: Any,
    val createdDate: String,
    val ecomOrdersId: Int,
    val guestFlag: String,
    val hearAbout: Any,
    val ipaddress: Any,
    val orderType: Any,
    val paymentType: Any,
    val sessionId: String,
    val specialNotes: Any,
    val status: String,
    val transactionFailed: String,
    val updatedDate: String,
    val userId: Any
)