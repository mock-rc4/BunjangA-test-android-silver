package com.example.risingtest.src.address

import com.example.risingtest.src.address.models.AddressRequest
import com.example.risingtest.src.address.models.AddressResponse
import com.example.risingtest.src.address.models.GetAddressResponse
import com.example.risingtest.src.product.models.ProductResponse
import retrofit2.Call
import retrofit2.http.*

interface AddresssInterface {

    @POST("/app/address")
    fun postAddress(
        @Body params: AddressRequest,
        @Header("X-access-token") token: String?
    ): Call<AddressResponse>

    @GET("/app/address")
    fun getAddress(
        @Header("X-access-token") token: String?,
    ): Call<GetAddressResponse>

}