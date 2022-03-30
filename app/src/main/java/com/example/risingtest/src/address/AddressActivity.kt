package com.example.risingtest.src.address

import android.content.Intent
import android.location.Address
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivitySetAddressBinding
import com.example.risingtest.src.address.models.AddressRequest
import com.example.risingtest.src.deliveryToBuy.models.DeliveryInfoResponse
import com.example.risingtest.src.kakaoAddress.KakaoActivity
import com.example.risingtest.src.register.RegisterService
import com.example.risingtest.src.register.models.PostSignUpRequest

data class AddressData(var name : String, var address : String, var phoneNumber : String)

class AddressActivity : BaseActivity<ActivitySetAddressBinding>(ActivitySetAddressBinding::inflate), AddressActivityView {

    var name : String = ""
    var phoneNumber : String = ""
    var address : String = ""
    var addressDecs : String = ""
    var defaultAddress : String = ""
    var userIdx : String = ""
    private lateinit var recyclerViewAdapter: AddressRecyclerViewAdapter
    var dataList = ArrayList<AddressData>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addAddress()
        editTextListner()
        addAddressBtnClick()

        Handler(Looper.getMainLooper()).postDelayed({
            initAddressRecycelrView()
            isEmpty()
        }, 500)
//        addressClick()
        binding.btnNext.visibility=View.GONE

        dataList.add(AddressData("제갈은","블라블라블라ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ","0102875928"))
        dataList.add(AddressData("제갈은","블라블라블라ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ","0102875928"))
    }

    // 주소 추가 눌렀을 때
    fun addAddress() {
        binding.tvAddAddress.setOnClickListener {
            binding.llManageAddress.visibility = View.GONE
            binding.llAddAddress.visibility = View.VISIBLE
            binding.btnNext.visibility=View.VISIBLE
            binding.rvAddressList.visibility = View.GONE
        }
    }

    // 주소 리사이클러뷰 장착
    fun initAddressRecycelrView() {
        recyclerViewAdapter = AddressRecyclerViewAdapter(this,dataList)
        binding.rvAddressList.adapter = recyclerViewAdapter
        binding.rvAddressList.layoutManager = LinearLayoutManager(this)

    }

    // 리사이클러뷰의 주소가 0개일 때
    fun isEmpty(){
        if(recyclerViewAdapter.itemCount==0){
            binding.rvAddressList.visibility=View.GONE
            binding.llAddAddressItemZero.visibility=View.VISIBLE
        }else {
            binding.rvAddressList.visibility=View.VISIBLE
            binding.llAddAddressItemZero.visibility=View.GONE
        }
    }

    // "주소" 클릭
//    fun addressClick(){
//        binding.llAddress.setOnClickListener {
//            var intent = Intent(this, KakaoActivity::class.java)
//            startActivity(intent)
//        }
//    }


    // 상세주소 입력할 때
    fun editTextListner() {
        binding.edtDetailAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // 입력하기 전에 조치
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // 입력난에 변화가 있을 시 조치
                if(binding.edtDetailAddress.length()<1){
                    binding.btnNext.isClickable = false
                    binding.btnNext.isEnabled = false
                }else {
                    binding.btnNext.isEnabled=true
                    binding.btnNext.isClickable = true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                // 입력 후 조치
            }

        })

    }

    // 주소 추가하기
    fun addAddressBtnClick(){
        binding.btnNext.setOnClickListener {
            name = binding.edtName.text.toString()
            phoneNumber = binding.edtPhoneNumber.text.toString()
            address = binding.edtAddress.text.toString()
            addressDecs = binding.edtDetailAddress.text.toString()
//            userIdx = binding.edtName.text.toString()
            userIdx="1"

//            val postRequest = AddressRequest(name = name, phoneNumber = phoneNumber,
//                address = address, addressDecs = addressDecs, defaultAddress = defaultAddress, userIdx = userIdx)
//            showLoadingDialog(this)
//            AddressService(this).tryPostAddress(postRequest)
        }
    }

    override fun onPostAddressSuccess(response: DeliveryInfoResponse) {
        if(response.code==1000){
            dataList.add(AddressData("제갈은","블라블라블라ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ","0102875928"))
        }
    }

    override fun onPostAddressFailure(message: String) {
    }
}