package com.example.risingtest.src.register.models


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("idx")
    val idx: Int?,
    @SerializedName("jwt")
    val jwt: String?,
    @SerializedName("phoneNumber")
    val phoneNumber: String?,
    @SerializedName("shopName")
    val shopName: String?,
    @SerializedName("userBirth")
    val userBirth: String?,
    @SerializedName("userName")
    val userName: String?,
    @SerializedName("userPwd")
    val userPwd: String?
)