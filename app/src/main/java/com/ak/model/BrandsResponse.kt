package com.ak.model

data class BrandsResponse(
    val brandsList: List<Brands>,
    val success: Boolean,
    val successCode: String
)