package com.example.risingtest.src.address

import com.example.risingtest.src.address.models.*
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

    @PATCH("/app/address/{addressIdx}")
    fun modifyAddress(
        @Body params: AddressModifyRequest,
        @Header("X-access-token") token: String?,
        @Path("addressIdx") addressIdx : Int
    ): Call<AddressModifyResponse>

    @PATCH("app/address/{addressIdx}/delete")
    fun deleteAddress(
        @Header("X-access-token") token: String?,
        @Path("addressIdx") addressIdx : Int
    ): Call<AddressDeleteResponse>

}