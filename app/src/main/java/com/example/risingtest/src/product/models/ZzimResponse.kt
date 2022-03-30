package com.example.risingtest.src.product.models


import com.google.gson.annotations.SerializedName

data class ZzimResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("isSuccess")
    val isSuccess: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("result")
    val result: ResultX?
)