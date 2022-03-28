package com.example.risingtest.src.deliveryToBuy

import android.annotation.SuppressLint
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import com.example.risingtest.R
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityDeliveryToBuyBinding
import com.github.ybq.android.spinkit.animation.AnimationUtils.start


class DeliveryToBuyActivity : BaseActivity<ActivityDeliveryToBuyBinding>(ActivityDeliveryToBuyBinding::inflate) {

    private var animationDrawable: AnimationDrawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        coinAni()
        PayWayBtnCheck()
    }

    // 코인 돌아가는 애니메이션
    fun coinAni(){

        animationDrawable = binding.ivAnimationCoin.background as AnimationDrawable
        animationDrawable!!.start()
    }

    // 결제수단 클릭했을 경우
    @SuppressLint("ResourceAsColor")
    fun PayWayBtnCheck(){
        binding.rbtnBunjangPay.setOnClickListener {
            binding.tvBunjangPay.setTextColor(R.color.black)
            binding.tvOtherPay.setTextColor(R.color.dark_gray)
            binding.tvRegisterPayWay.setTextColor(R.color.light_light_light_light_gray)
            binding.rbtnOtherPay.isChecked=false
        }

        binding.rbtnOtherPay.setOnClickListener {
            binding.tvBunjangPay.setTextColor(R.color.dark_gray)
            binding.tvOtherPay.setTextColor(R.color.black)
            binding.tvRegisterPayWay.setTextColor(R.color.light_gray)
            binding.rbtnBunjangPay.isChecked=false
        }
    }
}