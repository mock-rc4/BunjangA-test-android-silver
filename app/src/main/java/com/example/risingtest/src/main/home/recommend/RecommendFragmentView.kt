package com.example.risingtest.src.main.home.recommend

import com.example.risingtest.src.main.home.recommend.models.RecommendResponse

interface RecommendFragmentView {

    fun onGetRecommendSuccess(response: RecommendResponse)

    fun onGetRecommendFailure(message: String)
}