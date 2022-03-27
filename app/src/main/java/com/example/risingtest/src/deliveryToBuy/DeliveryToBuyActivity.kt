package com.example.risingtest.src.deliveryToBuy

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityDeliveryToBuyBinding
import com.github.ybq.android.spinkit.animation.AnimationUtils.start


class DeliveryToBuyActivity : BaseActivity<ActivityDeliveryToBuyBinding>(ActivityDeliveryToBuyBinding::inflate) {

    private var animationDrawable: AnimationDrawable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        coinAni()
    }

    // 코인 돌아가는 애니메이션
    fun coinAni(){

        animationDrawable = binding.ivAnimationCoin.background as AnimationDrawable
        animationDrawable!!.start()
    }
}