package com.example.risingtest.src.main.home.recommend.models


import com.google.gson.annotations.SerializedName

data class RecommendResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("isSuccess")
    val isSuccess: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("result")
    val result: List<List<Result>>?
)