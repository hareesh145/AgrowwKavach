package com.ak.model

data class CreditCardListResponse(
    val creditCardsList: List<CreditCard>,
    val success: Boolean,
    val successCode: String
)