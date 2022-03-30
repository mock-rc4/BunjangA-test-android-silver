package com.example.risingtest.src.address

import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.address.models.AddressRequest
import com.example.risingtest.src.deliveryToBuy.DeliveryInterface
import com.example.risingtest.src.deliveryToBuy.models.DeliveryInfoResponse
import com.example.risingtest.src.deliveryToBuy.models.DeliveryRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddressService(val view: AddressActivityView) {

    val token : String? = ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN,null)

    fun tryPostAddress(addressRequest: AddressRequest){
        val retrofitInterface = ApplicationClass.sRetrofit.create(AddresssInterface::class.java)
        retrofitInterface.postAddress(addressRequest,token).enqueue(object :
            Callback<DeliveryInfoResponse> {
            override fun onResponse(call: Call<DeliveryInfoResponse>, response: Response<DeliveryInfoResponse>) {
                view.onPostAddressSuccess(response.body() as DeliveryInfoResponse)
            }

            override fun onFailure(call: Call<DeliveryInfoResponse>, t: Throwable) {
                view.onPostAddressFailure(t.message ?: "통신 오류")
            }
        })
    }

}