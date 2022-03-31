package com.example.risingtest.src.address

import com.example.risingtest.src.address.models.AddressDeleteResponse

interface DeleteAddressActivityView {

    fun onDeleteAddressSuccess(response: AddressDeleteResponse)

    fun onDeleteAddressFailure(message: String)
}