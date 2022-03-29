package com.example.risingtest.src.deliveryToBuy

import com.example.risingtest.src.deliveryToBuy.models.DeliveryInfoResponse
import com.example.risingtest.src.deliveryToBuy.models.DeliveryRequest
import com.example.risingtest.src.register.models.PostSignUpRequest
import retrofit2.Call
import retrofit2.http.*

interface DeliveryInterface {

    @POST("app/products/{productIdx}/payment")
    fun postDelivery(
        @Body params: DeliveryRequest,
        @Header("X-access-token") token: String?,
        @Path("productIdx") productIdx: String,
    ): Call<DeliveryInfoResponse>
}