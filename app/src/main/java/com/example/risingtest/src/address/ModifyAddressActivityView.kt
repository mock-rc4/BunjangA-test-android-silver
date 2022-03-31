package com.example.risingtest.src.address

import com.example.risingtest.src.address.models.AddressModifyResponse

interface ModifyAddressActivityView {

    fun onModifyAddressSuccess(response: AddressModifyResponse)

    fun onModifyAddressFailure(message: String)
}