package com.example.risingtest.src.register

import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.passwd.models.LoginResponse
import com.example.risingtest.src.register.models.PostSignUpRequest
import com.example.risingtest.src.register.models.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterService(val view: RegisterActivityView) {

    fun tryPostSignUp(postSignUpRequest: PostSignUpRequest){
        val retrofitInterface = ApplicationClass.sRetrofit.create(RegisterInterface::class.java)
        retrofitInterface.postSignUp(postSignUpRequest).enqueue(object :
            Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                view.onPostSignUpSuccess(response.body() as SignUpResponse)
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                view.onPostSignUpFailure(t.message ?: "통신 오류")
            }
        })
    }
}