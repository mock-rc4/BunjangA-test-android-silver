package com.example.risingtest.src.address

import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.address.models.AddressDeleteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteAddressService(val view: DeleteAddressActivityView) {

    val user_token ="eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoxLCJpYXQiOjE2NDc5NzE1NzAsImV4cCI6MTY0OTQ0Mjc5OX0.vzZ5-b06YbnMCR-TtqgT4TV7kp3wFk6SgtyKyoeMU98"

    fun tryDeleteAddress(addressIdx : Int){
        val retrofitInterface = ApplicationClass.sRetrofit.create(AddresssInterface::class.java)
        retrofitInterface.deleteAddress(user_token, addressIdx).enqueue(object :
            Callback<AddressDeleteResponse> {
            override fun onResponse(call: Call<AddressDeleteResponse>, response: Response<AddressDeleteResponse>) {
                view.onDeleteAddressSuccess(response.body() as AddressDeleteResponse)
            }

            override fun onFailure(call: Call<AddressDeleteResponse>, t: Throwable) {
                view.onDeleteAddressFailure(t.message ?: "통신 오류")
            }
        })
    }
}