package com.ak.model

data class Categories(
    val categoryCode: String,
    val categoryDescription: Any,
    val categoryName: String,
    val deleteDate: String,
    val deleteFlag: String,
    val ecomCategoriesId: Int,
    val image: Any,
    val imageAltTag: Any,
    val parentCode: String
)