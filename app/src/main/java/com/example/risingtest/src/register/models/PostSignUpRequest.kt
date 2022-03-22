package com.example.risingtest.src.register.models

import com.google.gson.annotations.SerializedName

data class PostSignUpRequest (
    @SerializedName("shopName") val shopName : String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("userName") val userName: String,
    @SerializedName("userBirth") val userBirth: String,
    @SerializedName("userPwd") val userPwd: String
)