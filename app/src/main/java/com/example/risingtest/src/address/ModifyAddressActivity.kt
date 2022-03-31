package com.example.risingtest.src.address

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityModifyAddressBinding
import com.example.risingtest.src.address.models.AddressModifyRequest
import com.example.risingtest.src.address.models.AddressModifyResponse

class ModifyAddressActivity : BaseActivity<ActivityModifyAddressBinding>(ActivityModifyAddressBinding::inflate), ModifyAddressActivityView {

    private lateinit var name : String
    private lateinit var address_first : String
    private lateinit var address_detail : String
    private lateinit var phoneNumber : String
    private lateinit var userIdx : String
    private lateinit var addressPosition : String
    private var defaultAddress = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getInfo()

//        val full_address = address.split(" ")
//        address=full_address?.get(0)
//        address_detail=full_address?.get(1)

        setInfo()
        btnclick()

    }



    //주소값 가져오기
    fun getInfo(){
        if (intent.hasExtra("name")) {
            name = intent.getStringExtra("name").toString()
            address_first = intent.getStringExtra("address").toString()
            address_detail = intent.getStringExtra("addressDesc").toString()
            phoneNumber = intent.getStringExtra("phoneNumber").toString()
            userIdx = intent.getStringExtra("userIdx").toString()
            addressPosition = intent.getStringExtra("addressPosition").toString()
            //userIdx = intent.getStringExtra("position").toString()

        }
        else {
            Toast.makeText(this, "이름값이 없습니다!", Toast.LENGTH_SHORT).show()
        }
    }

    // 주소값 설정
    fun setInfo() {
        binding.edtName.setText(name)
        binding.edtPhoneNumber.setText(phoneNumber)
        binding.edtAddress.setText(address_first)
        binding.edtDetailAddress.setText(address_detail)
    }

    // 주소 수정 완료
    fun btnclick() {
        binding.btnNext.setOnClickListener {
            name = binding.edtName.text.toString()
            phoneNumber = binding.edtPhoneNumber.text.toString()
            address_first = binding.edtAddress.text.toString()
            address_detail = binding.edtDetailAddress.text.toString()
            val position = Integer.parseInt(addressPosition)

            val postRequest = AddressModifyRequest(name = name, phoneNumber = phoneNumber,
                address = address_first, addressDesc = address_detail, defaultAddress = defaultAddress, userIdx = userIdx)
            ModifyAddressService(this).tryModifyAddress(postRequest, position)
        }
    }

    override fun onModifyAddressSuccess(response: AddressModifyResponse) {
        if(response.code==1000){
            showCustomToast("주소가 수정되었습니다.")
            val intent = Intent(this, AddressActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onModifyAddressFailure(message: String) {
    }
}
