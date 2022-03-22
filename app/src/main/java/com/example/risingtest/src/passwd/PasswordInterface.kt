package com.example.risingtest.src.passwd

import com.example.risingtest.src.passwd.models.LoginResponse
import com.example.risingtest.src.passwd.models.PostLoginRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PasswordInterface {

//    @POST("/api/search.api?output=json")
//    fun getBooksByName(
//        @Query("phone_number") phone_number : String,
//    ): Call<LoginResponse>

    @POST("/app/users/logIn")
    fun postLogin(@Body params: PostLoginRequest): Call<LoginResponse>

//    @GET("/api/bestSeller.api?output=json&categoryId=101")
//    fun getBestSellerBooks(
//        @Query("key") apiKey: String
//    ): Call<BestSellerDto>
}