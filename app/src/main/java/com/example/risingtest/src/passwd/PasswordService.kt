package com.example.risingtest.src.passwd

import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.passwd.models.LoginResponse
import com.example.risingtest.src.passwd.models.PostLoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PasswordService(val view: PasswordActivityView) {

    fun tryPostLogin(postLoginRequest: PostLoginRequest){
        val retrofitInterface = ApplicationClass.sRetrofit.create(PasswordInterface::class.java)
        retrofitInterface.postLogin(postLoginRequest).enqueue(object :
            Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                view.onPostUserSuccess(response.body() as LoginResponse)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                view.onPostUserFailure(t.message ?: "통신 오류")
            }
        })
    }
}