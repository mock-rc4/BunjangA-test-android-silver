package com.example.risingtest.src.register

import com.example.risingtest.src.register.models.SignUpResponse

interface RegisterActivityView {

    fun onPostSignUpSuccess(response: SignUpResponse)

    fun onPostSignUpFailure(message: String)

}