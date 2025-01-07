package com.ak.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Districts(
    val agrowwDistrictsId: Int,
    val agrowwStatesId: Int,
    val districtsName: String
) : Parcelable