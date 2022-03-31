package com.example.risingtest.src.main.home.recommend

import com.example.risingtest.src.main.home.recommend.models.RecommendResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RecommendInterface {



    @GET("/app/home")
    fun getRecommnedProducts(
        @Header("X-access-token") token: String?,
    ): Call<RecommendResponse>

}