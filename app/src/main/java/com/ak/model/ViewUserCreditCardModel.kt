package com.ak.model

data class ViewUserCreditCardModel(
    val creditCard: CreditCard,
    val success: Boolean,
    val successCode: String
)