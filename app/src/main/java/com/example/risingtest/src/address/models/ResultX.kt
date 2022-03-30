package com.example.risingtest.src.address.models


import com.google.gson.annotations.SerializedName

data class ResultX(
    @SerializedName("address")
    val address: String?,
    @SerializedName("addressDesc")
    val addressDesc: String?,
    @SerializedName("defaultAddress")
    val defaultAddress: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phoneNumber")
    val phoneNumber: String?,
    @SerializedName("userIdx")
    val userIdx: Int?
)