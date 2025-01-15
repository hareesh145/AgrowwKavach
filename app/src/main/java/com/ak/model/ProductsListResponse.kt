package com.ak.model

data class ProductsListResponse(
    val productsList: List<Products>,
    val success: Boolean,
    val successCode: String
)