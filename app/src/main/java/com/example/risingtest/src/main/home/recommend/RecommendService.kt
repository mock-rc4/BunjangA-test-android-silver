package com.example.risingtest.src.main.home.recommend

import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.src.main.home.recommend.models.RecommendResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendService(val view: RecommendFragmentView) {

    val token : String? = ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN,null)

    fun tryRecommendProducts(){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(RecommendInterface::class.java)
        homeRetrofitInterface.getRecommnedProducts(token).enqueue(object : Callback<RecommendResponse> {
            override fun onResponse(call: Call<RecommendResponse>, response: Response<RecommendResponse>) {
                view.onGetRecommendSuccess(response.body() as RecommendResponse)
            }

            override fun onFailure(call: Call<RecommendResponse>, t: Throwable) {
                view.onGetRecommendFailure(t.message ?: "통신 오류")
            }
        })
    }
}