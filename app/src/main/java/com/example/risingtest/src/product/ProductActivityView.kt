package com.example.risingtest.src.product

import com.example.risingtest.src.deliveryToBuy.models.DeliveryInfoResponse
import com.example.risingtest.src.product.models.ProductResponse
import com.example.risingtest.src.product.models.ZzimResponse
import java.time.zone.ZoneRulesException

interface ProductActivityView {

    fun onGetProductInfoSuccess(response: ProductResponse)

    fun onGetProductInfoFailure(message: String)

    fun onPostZzimSuccess(response: ZzimResponse)

    fun onPostZzimFailure(message: String)

}