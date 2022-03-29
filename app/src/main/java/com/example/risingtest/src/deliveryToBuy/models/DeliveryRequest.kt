package com.example.risingtest.src.deliveryToBuy.models


import com.google.gson.annotations.SerializedName

data class DeliveryRequest(
    @SerializedName("address")
    val address: String?,
    @SerializedName("paymentMethod")
    val paymentMethod: String?,
    @SerializedName("point")
    val point: String?,
    @SerializedName("productIdx")
    val productIdx: String?,
    @SerializedName("safetyTax")
    val safetyTax: String?,
    @SerializedName("totalPaymentAmount")
    val totalPaymentAmount: String?,
    @SerializedName("transactionMethod")
    val transactionMethod: String?
)