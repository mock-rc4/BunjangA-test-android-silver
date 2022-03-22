package com.example.risingtest.src.passwd

import com.example.risingtest.src.passwd.models.LoginResponse

interface PasswordActivityView {

    fun onPostUserSuccess(response: LoginResponse)

    fun onPostUserFailure(message: String)

}