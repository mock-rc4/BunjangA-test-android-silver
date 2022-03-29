package com.example.risingtest.src.product

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.risingtest.R
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.ViewPagerProductBinding

class ProductImgFragment(val image: String) : BaseFragment<ViewPagerProductBinding>(ViewPagerProductBinding::bind, R.layout.view_pager_product) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load(image)
            .into(binding.ivProductImg)

    }

}