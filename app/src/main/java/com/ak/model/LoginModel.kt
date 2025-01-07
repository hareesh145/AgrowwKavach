package com.ak.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginModel (
    val mobilePhone: String,
    val password: String
):Parcelable