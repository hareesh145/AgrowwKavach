package com.ak.model

import com.google.gson.annotations.SerializedName

data class AddShippingModel(
    @SerializedName("userId"       ) var userId       : Int?    = null,
    @SerializedName("name"         ) var name         : String? = null,
    @SerializedName("addressLine1" ) var addressLine1 : String? = null,
    @SerializedName("addressLine2" ) var addressLine2 : String? = null,
    @SerializedName("city"         ) var city         : String? = null,
    @SerializedName("state"        ) var state        : String? = null,
    @SerializedName("countryCode"  ) var countryCode  : String? = null,
    @SerializedName("postalCode"   ) var postalCode   : String? = null,
    @SerializedName("defaultFlag"  ) var defaultFlag  : String? = null

)