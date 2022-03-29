package com.example.risingtest.src.deliveryToBuy

import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.deliveryToBuy.models.DeliveryInfoResponse
import com.example.risingtest.src.deliveryToBuy.models.DeliveryRequest
import com.example.risingtest.src.passwd.PasswordActivityView
import com.example.risingtest.src.passwd.PasswordInterface
import com.example.risingtest.src.passwd.models.LoginResponse
import com.example.risingtest.src.passwd.models.PostLoginRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeliveryService(val view: DeliveryToBuyActivityView) {

    val token : String? = ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN,null)

    fun tryPostDelivery(deliveryRequest: DeliveryRequest, productIdx:String){
        val retrofitInterface = ApplicationClass.sRetrofit.create(DeliveryInterface::class.java)
        retrofitInterface.postDelivery(deliveryRequest, token, productIdx).enqueue(object :
            Callback<DeliveryInfoResponse> {
            override fun onResponse(call: Call<DeliveryInfoResponse>, response: Response<DeliveryInfoResponse>) {
                view.onPostPaySuccess(response.body() as DeliveryInfoResponse)
            }

            override fun onFailure(call: Call<DeliveryInfoResponse>, t: Throwable) {
                view.onPostPayFailure(t.message ?: "통신 오류")
            }
        })
    }

}