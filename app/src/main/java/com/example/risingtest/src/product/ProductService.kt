package com.example.risingtest.src.product

import android.content.Intent
import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.main.home.recommend.RecommendFragmentView
import com.example.risingtest.src.product.models.ProductResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductService(val view: ProductActivityView) {

    val token : String? = ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN,null)

    fun tryGetProductInfoBooks(productIdx : Int){
        val retrofitInterface = ApplicationClass.sRetrofit.create(ProductInterface::class.java)
        retrofitInterface.getProductInfo(token,productIdx).enqueue(object :
            Callback<ProductResponse> {
            override fun onResponse(call: Call<ProductResponse>, response: Response<ProductResponse>) {
                view.onGetProductInfoSuccess(response.body() as ProductResponse)
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                view.onGetProductInfoFailure(t.message ?: "통신 오류")
            }
        })
    }

}