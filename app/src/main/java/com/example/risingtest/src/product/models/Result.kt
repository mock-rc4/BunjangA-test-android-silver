package com.example.risingtest.src.product.models


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("avgStar")
    val avgStar: Double?,
    @SerializedName("categoryName")
    val categoryName: String?,
    @SerializedName("directtrans")
    val directtrans: String?,
    @SerializedName("follower")
    val follower: Int?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("includeFee")
    val includeFee: Int?,
    @SerializedName("likeCount")
    val likeCount: Int?,
    @SerializedName("myLike")
    val myLike: Int?,
    @SerializedName("pidx")
    val pidx: Int?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("productCondition")
    val productCondition: Int?,
    @SerializedName("productDesc")
    val productDesc: String?,
    @SerializedName("productName")
    val productName: String?,
    @SerializedName("profileImage")
    val profileImage: String?,
    @SerializedName("saftyPay")
    val saftyPay: Int?,
    @SerializedName("shopName")
    val shopName: String?,
    @SerializedName("tag")
    val tag: String?,
    @SerializedName("uidx")
    val uidx: Int?,
    @SerializedName("viewCount")
    val viewCount: Int?
)