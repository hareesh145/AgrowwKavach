package com.ak.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ColdStorageResponse(
    val coldStoragesLocationsList: List<ColdStoragesLocations>,
    val success: Boolean,
    val successCode: String
) : Parcelable