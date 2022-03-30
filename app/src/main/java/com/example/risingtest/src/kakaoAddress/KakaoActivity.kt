package com.example.risingtest.src.kakaoAddress

import android.content.Intent
import android.os.Bundle
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityKakaoAddressBinding
import com.example.risingtest.src.address.AddressActivity

class KakaoActivity : BaseActivity<ActivityKakaoAddressBinding>(ActivityKakaoAddressBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun onClick() {
        val address = binding.edtInputAddress.text
        val intent = Intent(this, AddressActivity::class.java)
        startActivity(intent)
        finish()
    }
}