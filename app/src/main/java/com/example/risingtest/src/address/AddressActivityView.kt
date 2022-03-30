package com.example.risingtest.src.address

import com.example.risingtest.src.deliveryToBuy.models.DeliveryInfoResponse

interface AddressActivityView {

    fun onPostAddressSuccess(response: DeliveryInfoResponse)

    fun onPostAddressFailure(message: String)

}