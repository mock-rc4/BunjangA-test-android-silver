package com.example.risingtest.src.main.home.recommend.models


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("adDesc")
    val adDesc: String?,
    @SerializedName("adImageUrl")
    val adImageUrl: String?,
    @SerializedName("adLink")
    val adLink: String?,
    @SerializedName("adTittle")
    val adTittle: String?,
    @SerializedName("createAt")
    val createAt: String?,
    @SerializedName("directtrans")
    val directtrans: Any?,
    @SerializedName("idx")
    val idx: Int?,
    @SerializedName("imageUrl")
    val imageUrl: Any?,
    @SerializedName("myLike")
    val myLike: Int?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("productLike")
    val productLike: Int?,
    @SerializedName("productName")
    val productName: String?,
    @SerializedName("saftyPay")
    val saftyPay: Int?
)
