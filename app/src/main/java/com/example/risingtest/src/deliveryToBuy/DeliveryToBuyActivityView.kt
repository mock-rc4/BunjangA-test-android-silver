package com.example.risingtest.src.deliveryToBuy

import com.example.risingtest.src.deliveryToBuy.models.DeliveryInfoResponse
import com.example.risingtest.src.main.home.recommend.models.RecommendResponse
import com.example.risingtest.src.passwd.models.LoginResponse

interface DeliveryToBuyActivityView {

    fun onPostPaySuccess(response: DeliveryInfoResponse)

    fun onPostPayFailure(message: String)
}