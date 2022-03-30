package com.example.risingtest.src.product

import android.content.Intent
import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.product.models.ProductResponse
import com.example.risingtest.src.product.models.ZzimRequest
import com.example.risingtest.src.product.models.ZzimResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductService(val view: ProductActivityView) {

    val token : String? = ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN,null)
    val x_token = "eyJ0eXBlIjoiand0IiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VySWR4Ijo0NCwiaWF0IjoxNjQ4MDQ0NTE1LCJleHAiOjE2NDk1MTU3NDR9.saq6O-L27FHtjgXJ0724oigbvu-wKsx97jzvAWgI18o"

    fun tryGetProductInfoBooks(productIdx : Int){
        val retrofitInterface = ApplicationClass.sRetrofit.create(ProductInterface::class.java)
        retrofitInterface.getProductInfo(x_token,productIdx).enqueue(object :
            Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                view.onGetProductInfoSuccess(response.body() as ProductResponse)
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                view.onGetProductInfoFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPostZzim(zzimRequest: ZzimRequest){
        val post_retrofitInterface = ApplicationClass.sRetrofit.create(ProductInterface::class.java)
        post_retrofitInterface.postZzim(x_token, zzimRequest).enqueue(object :
            Callback<ZzimResponse> {
            override fun onResponse(call: Call<ZzimResponse>, response: Response<ZzimResponse>) {
                view.onPostZzimSuccess(response.body() as ZzimResponse)
            }

            override fun onFailure(call: Call<ZzimResponse>, t: Throwable) {
                view.onPostZzimFailure(t.message ?: "통신 오류")
            }
        })
    }

}
