package com.example.risingtest.src.address

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivitySetAddressBinding
import com.example.risingtest.src.kakaoAddress.KakaoActivity

class AddressActivity : BaseActivity<ActivitySetAddressBinding>(ActivitySetAddressBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addAddress()
        editTextListner()
        addressClick()
        binding.btnNext.visibility=View.GONE
    }

    // 주소 추가 눌렀을 때
    fun addAddress() {
        binding.tvAddAddress.setOnClickListener {
            binding.llManageAddress.visibility = View.GONE
            binding.llAddAddress.visibility = View.VISIBLE
            binding.btnNext.visibility=View.VISIBLE
        }
    }

    // "주소" 클릭
    fun addressClick(){
        binding.llAddress.setOnClickListener {
            var intent = Intent(this, KakaoActivity::class.java)
            startActivity(intent)
        }
    }


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
}