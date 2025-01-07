package com.ak.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponseModel(
    val success: Boolean,
    val successCode: String,
    val userInfo: UserInfo,
    val errorMessage: String
) : Parcelable