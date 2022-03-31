package com.example.risingtest.src.address

import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.address.models.AddressRequest
import com.example.risingtest.src.address.models.AddressResponse
import com.example.risingtest.src.address.models.GetAddressResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddressService(val view: AddressActivityView) {

    val token : String? = ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN,null)
    val user_token ="eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoxLCJpYXQiOjE2NDc5NzE1NzAsImV4cCI6MTY0OTQ0Mjc5OX0.vzZ5-b06YbnMCR-TtqgT4TV7kp3wFk6SgtyKyoeMU98"
    var get_user_token ="eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4IjoxLCJpYXQiOjE2NDc5NzE1NzAsImV4cCI6MTY0OTQ0Mjc5OX0.vzZ5-b06YbnMCR-TtqgT4TV7kp3wFk6SgtyKyoeMU98"

    fun tryPostAddress(addressRequest: AddressRequest){
        val retrofitInterface = ApplicationClass.sRetrofit.create(AddresssInterface::class.java)
        retrofitInterface.postAddress(addressRequest,user_token).enqueue(object :
            Callback<AddressResponse> {
            override fun onResponse(call: Call<AddressResponse>, response: Response<AddressResponse>) {
                view.onPostAddressSuccess(response.body() as AddressResponse)
            }

            override fun onFailure(call: Call<AddressResponse>, t: Throwable) {
                view.onPostAddressFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryGetAddress(){
        val retrofitInterface = ApplicationClass.sRetrofit.create(AddresssInterface::class.java)
        retrofitInterface.getAddress(user_token).enqueue(object :
            Callback<GetAddressResponse> {
            override fun onResponse(call: Call<GetAddressResponse>, response: Response<GetAddressResponse>) {
                view.onGetAddressSuccess(response.body() as GetAddressResponse)
            }

            override fun onFailure(call: Call<GetAddressResponse>, t: Throwable) {
                view.onGetAddressFailure(t.message ?: "통신 오류")
            }
        })
    }

}