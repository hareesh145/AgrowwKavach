package com.ak.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DistrictResponse(
    val districtsList: List<Districts>? = null,
) : Parcelable