package com.example.risingtest.src.main.home.brand

import android.os.Bundle
import android.view.View
import com.example.risingtest.R
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.FragmentChildHomeBrandBinding
import com.example.risingtest.src.main.home.recommend.RecommendFragment

class BrandFragment : BaseFragment<FragmentChildHomeBrandBinding>(FragmentChildHomeBrandBinding::bind, R.layout.fragment_child_home_brand) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun newInstant() : BrandFragment {
        val args = Bundle()
        val frag = BrandFragment()
        frag.arguments=args
        return frag
    }
}