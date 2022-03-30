package com.example.risingtest.src.product

import com.example.risingtest.src.deliveryToBuy.models.DeliveryInfoResponse
import com.example.risingtest.src.deliveryToBuy.models.DeliveryRequest
import com.example.risingtest.src.product.models.ProductResponse
import com.example.risingtest.src.product.models.ZzimRequest
import com.example.risingtest.src.product.models.ZzimResponse
import retrofit2.Call
import retrofit2.http.*

interface ProductInterface {

    @GET("/app/products/{productIdx}")
    fun getProductInfo(
        @Header("X-access-token") token: String?,
        @Path("productIdx") productIdx: Int
    ): Call<ProductResponse>

    @POST("/app/favorites")
    fun postZzim(
        @Header("X-access-token") token: String?,
        @Body params: ZzimRequest,
    ): Call<ZzimResponse>

}