package com.ak.model

data class AddColdStorage(
    val agrowwDistrictsId: Int,
    val capacityInMt: String,
    val managerName: String,
    val mobileNumber: String,
    val serialNo: String,
    val storageAddress: String,
    val storageName: String,
    val type: String
)