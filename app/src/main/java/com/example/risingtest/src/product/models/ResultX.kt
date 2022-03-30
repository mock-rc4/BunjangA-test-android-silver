package com.example.risingtest.src.product.models


import com.google.gson.annotations.SerializedName

data class ResultX(
    @SerializedName("productIdx")
    val productIdx: Int?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("userIdx")
    val userIdx: Int?
)