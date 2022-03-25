package com.example.risingtest.src.product

import com.example.risingtest.src.product.models.ProductResponse

interface ProductActivityView {

    fun onGetProductInfoSuccess(response: ProductResponse)

    fun onGetProductInfoFailure(message: String)

}