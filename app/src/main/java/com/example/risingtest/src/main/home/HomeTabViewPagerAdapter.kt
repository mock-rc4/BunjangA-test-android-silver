package com.example.risingtest.src.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.risingtest.src.main.home.brand.BrandFragment
import com.example.risingtest.src.main.home.recommend.RecommendFragment


class HomeTabViewPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        val fragment = when(position){
            0->RecommendFragment().newInstant()
            1->BrandFragment().newInstant()
            else -> RecommendFragment().newInstant()
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val title = when(position){
            0->"추천상품"
            1->"브랜드"
            else->"추천상품"
        }
        return title
    }


}