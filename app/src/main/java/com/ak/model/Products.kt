package com.ak.model


data class Products(
    val addedDate: String,
    val additionalAttributes: Any,
    val attachmentFiles: Any,
    val brandId: Int,
    val deleteDate: String,
    val deleteFlag: String,
    val ecomAgrowwItemId: Int,
    val ecomCategoriesId: Int,
    val freeFreightEligible: String,
    val imageURL: Any,
    val longDesc: Any,
    val mfrName: String,
    val mfrPartNum: String,
    val minSaleQty: Any,
    val overview: Any,
    val pkgQty: Int,
    val searchableKeyword: Any,
    val shipType: Any,
    val shortDesc: String,
    val supplierId: Int,
    val supplierPartNum: String,
    val uom: String,
    val updatedDate: String,
    val warrantyText: Any,
    val price: Double,
    val costPrice: Double,
    val sellPrice: Double,
    val shipWeight: Double,
)

