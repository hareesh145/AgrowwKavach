package com.ak.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponseModel(
    val success: Boolean? = null,
    val successCode: String = "",
    val userInfo: UserInfo? = null,
    val errorMessage: String? = null,
) : Parcelable