package com.ak.model

data class AgrowwProductsResponse(
    val productInfo: Products,
    val success: Boolean,
    val successCode: String,
    val recommendedProducts: List<Products>
)