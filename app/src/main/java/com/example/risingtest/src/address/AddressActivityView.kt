package com.example.risingtest.src.address

import com.example.risingtest.src.address.models.AddressResponse
import com.example.risingtest.src.address.models.GetAddressResponse

interface AddressActivityView {

    fun onPostAddressSuccess(response: AddressResponse)

    fun onPostAddressFailure(message: String)

    fun onGetAddressSuccess(response: GetAddressResponse)

    fun onGetAddressFailure(message: String)

}