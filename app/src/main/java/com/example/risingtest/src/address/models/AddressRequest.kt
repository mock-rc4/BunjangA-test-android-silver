package com.example.risingtest.src.address.models

import com.google.gson.annotations.SerializedName

data class AddressRequest(
    @SerializedName("name")
    val name: String?,
    @SerializedName("phoneNumber")
    val phoneNumber: String?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("addressDecs")
    val addressDecs: String?,
    @SerializedName("defaultAddress")
    val defaultAddress: String?,
    @SerializedName("userIdx")
    val userIdx: String?
)