package com.ak.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class States(
    val agrowwStatesId: Int,
    val stateName: String
) : Parcelable