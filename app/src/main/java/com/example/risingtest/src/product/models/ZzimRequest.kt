package com.example.risingtest.src.product.models

import com.google.gson.annotations.SerializedName

data class ZzimRequest (
    @SerializedName("userIdx")
    val userIdx: String?,
    @SerializedName("productIdx")
    val productIdx: String?
    )