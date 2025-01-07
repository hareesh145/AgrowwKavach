package com.ak.model

data class AddSupplierModel(
    val addressLine1: String,
    val addressLine2: String,
    val city: String,
    val companyWebsite: String,
    val countryCode: String,
    val officeFax: String,
    val officePhone: String,
    val postalCode: String,
    val state: String,
    val supplierDunsNum: String,
    val supplierName: String
)