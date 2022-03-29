package com.example.risingtest.src.deliveryToBuy.models


import com.example.risingtest.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class DeliveryInfoResponse(
//    @SerializedName("code")
//    val code: Int?,
//    @SerializedName("isSuccess")
//    val isSuccess: Boolean?,
//    @SerializedName("message")
//    val message: String?,
    @SerializedName("result")
    val result: Result?
) : BaseResponse()