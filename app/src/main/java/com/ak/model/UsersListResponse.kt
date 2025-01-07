package com.ak.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsersListResponse(
    val usersList: List<UserInfo>,
    val success: Boolean,
    val successCode: Int
) : Parcelable