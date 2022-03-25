package com.example.risingtest.src.product

import com.example.risingtest.src.product.models.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductInterface {

    @GET("/app/products/:productIdx")
    fun getProductInfoBooks(
        @Query("productIdx") productIdx: Int
    ): Call<ProductResponse>

}