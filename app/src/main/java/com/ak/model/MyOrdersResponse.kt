package com.ak.model

import com.google.gson.annotations.SerializedName

data class MyOrdersResponse(
    @SerializedName("success"     ) var success     : Boolean?              = null,
    @SerializedName("userOrders"  ) var userOrders  : ArrayList<UserOrders> = arrayListOf(),
    @SerializedName("successCode" ) var successCode : String?               = null
)

data class UserOrders(
    @SerializedName("ecomOrdersId"      ) var ecomOrdersId      : Int?    = null,
    @SerializedName("userId"            ) var userId            : Int?    = null,
    @SerializedName("guestFlag"         ) var guestFlag         : String? = null,
    @SerializedName("createdDate"       ) var createdDate       : String? = null,
    @SerializedName("updatedDate"       ) var updatedDate       : String? = null,
    @SerializedName("status"            ) var status            : String? = null,
    @SerializedName("checkoutFlag"      ) var checkoutFlag      : String? = null,
    @SerializedName("paymentType"       ) var paymentType       : String? = null,
    @SerializedName("sessionId"         ) var sessionId         : String? = null,
    @SerializedName("specialNotes"      ) var specialNotes      : String? = null,
    @SerializedName("orderType"         ) var orderType         : String? = null,
    @SerializedName("createdBy"         ) var createdBy         : String? = null,
    @SerializedName("transactionFailed" ) var transactionFailed : String? = null,
    @SerializedName("hearAbout"         ) var hearAbout         : String? = null,
    @SerializedName("ipaddress"         ) var ipaddress         : String? = null
)
