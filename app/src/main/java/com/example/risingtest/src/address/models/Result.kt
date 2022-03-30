package com.example.risingtest.src.address.models


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("address")
    val address: String?,
    @SerializedName("addressDesc")
    val addressDesc: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phoneNumber")
    val phoneNumber: String?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("userIdx")
    val userIdx: Int?
)