package com.example.risingtest.src.address

import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.address.models.AddressModifyRequest
import com.example.risingtest.src.address.models.AddressModifyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModifyAddressService(val view: ModifyAddressActivityView) {

    val user_token ="eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoxLCJpYXQiOjE2NDc5NzE1NzAsImV4cCI6MTY0OTQ0Mjc5OX0.vzZ5-b06YbnMCR-TtqgT4TV7kp3wFk6SgtyKyoeMU98"

    fun tryModifyAddress(addressModifyRequest: AddressModifyRequest, addressIdx : Int){
        val retrofitInterface = ApplicationClass.sRetrofit.create(AddresssInterface::class.java)
        retrofitInterface.modifyAddress(addressModifyRequest, user_token, addressIdx).enqueue(object :
            Callback<AddressModifyResponse> {
            override fun onResponse(call: Call<AddressModifyResponse>, response: Response<AddressModifyResponse>) {
                view.onModifyAddressSuccess(response.body() as AddressModifyResponse)
            }

            override fun onFailure(call: Call<AddressModifyResponse>, t: Throwable) {
                view.onModifyAddressFailure(t.message ?: "통신 오류")
            }
        })
    }
}