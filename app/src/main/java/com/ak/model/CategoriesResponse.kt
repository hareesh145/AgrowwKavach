package com.ak.model

data class CategoriesResponse(
    val categoriesList: List<Categories>,
    val success: Boolean,
    val successCode: String
)