package com.example.risingtest.src.address.models


import com.example.risingtest.config.BaseResponse
import com.google.gson.annotations.SerializedName

data class GetAddressResponse(
    @SerializedName("result")
    val result: List<ResultX>?
) : BaseResponse()