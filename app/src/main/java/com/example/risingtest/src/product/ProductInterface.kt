package com.example.risingtest.src.product

import com.example.risingtest.src.product.models.ProductResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ProductInterface {

    @GET("/app/products/{productIdx}")
    fun getProductInfo(
        @Header("X-access-token") token: String?,
        @Path("productIdx") productIdx: Int
    ): Call<ProductResponse>

}