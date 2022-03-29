package com.example.risingtest.src.deliveryToBuy.models


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("address")
    val address: String?,
    @SerializedName("buyerIdx")
    val buyerIdx: Int?,
    @SerializedName("idx")
    val idx: Int?,
    @SerializedName("paymentMethod")
    val paymentMethod: Int?,
    @SerializedName("point")
    val point: Int?,
    @SerializedName("productIdx")
    val productIdx: Int?,
    @SerializedName("safetyTax")
    val safetyTax: Int?,
    @SerializedName("totalPaymentAmount")
    val totalPaymentAmount: Int?,
    @SerializedName("transactionMethod")
    val transactionMethod: Int?
)