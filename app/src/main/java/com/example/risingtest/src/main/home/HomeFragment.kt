package com.example.risingtest.src.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.risingtest.R
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.FragmentHomeBinding
import com.example.risingtest.src.main.home.brand.BrandFragment
import com.example.risingtest.src.main.home.recommend.RecommendFragment
import com.google.android.material.tabs.TabLayout

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private lateinit var tab_recommend : RecommendFragment
    private lateinit var tab_brand : BrandFragment
    private lateinit var tabLayout : TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tab_fragment()

    }

    private fun tab_fragment(){
        tab_recommend = RecommendFragment()
        tab_brand = BrandFragment()
        tabLayout = binding.tlMain

        childFragmentManager.beginTransaction().add(R.id.cl_fragment_main, tab_recommend).commit()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        replaceView(tab_recommend)
                    }
                    1 -> {
                        replaceView(tab_brand)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun  replaceView(tab:Fragment) {
        var selectedFragment : Fragment? = null
        selectedFragment = tab
        selectedFragment?.let {
            childFragmentManager.beginTransaction()
                .replace(R.id.cl_fragment_main,it).commit()
        }
    }
}