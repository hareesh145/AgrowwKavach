package com.ak.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ColdStoragesLocations(
    val addedDate: String,
    val agrowwDistrictsId: Int,
    val capacityInMt: String,
    val coldStoragesLocationsId: Int,
    val deleteDate: String,
    val deleteFlag: String,
    val managerName: String,
    val mobileNumber: String,
    val serialNo: String,
    val storageAddress: String,
    val storageName: String,
    val type: String
) : Parcelable