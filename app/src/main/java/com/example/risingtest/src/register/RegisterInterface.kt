package com.example.risingtest.src.register

import com.example.risingtest.src.register.models.PostSignUpRequest
import com.example.risingtest.src.register.models.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterInterface {

    @POST("/app/users")
    fun postSignUp(@Body params: PostSignUpRequest): Call<SignUpResponse>

}