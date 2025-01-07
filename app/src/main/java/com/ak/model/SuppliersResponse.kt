package com.ak.model

data class SuppliersResponse(
    val success: Boolean,
    val successCode: String,
    val suppliersList: List<Suppliers>
)