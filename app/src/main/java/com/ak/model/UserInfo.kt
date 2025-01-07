package com.ak.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val activeFlag: String,
    val addedDate: String,
    val addressLine1: String,
    val addressLine2: String,
    val adminFlag: String,
    val city: String,
    val countryCode: String,
    val defaultPaymentFlag: String,
    val defaultPaymentType: String,
    val defaultShippingFlag: String,
    val deleteDate: String,
    val deleteFlag: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val mobilePhone: String,
    val password: String,
    val postalCode: String,
    val state: String,
    val userId: Int
) : Parcelable