package com.ak.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StateResponse(
    val statesList: List<States>,
    val success: Boolean
) : Parcelable