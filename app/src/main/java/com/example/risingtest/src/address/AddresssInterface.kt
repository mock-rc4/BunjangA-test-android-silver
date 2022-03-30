package com.example.risingtest.src.address

import com.example.risingtest.src.address.models.AddressRequest
import com.example.risingtest.src.deliveryToBuy.models.DeliveryInfoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AddresssInterface {

    @POST("/app/address")
    fun postAddress(
        @Body params: AddressRequest,
        @Header("X-access-token") token: String?
    ): Call<DeliveryInfoResponse>

}