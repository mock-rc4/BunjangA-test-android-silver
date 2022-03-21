package com.example.risingtest.src.main.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.risingtest.R
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.FragmentHomeBinding
import com.example.risingtest.src.main.home.brand.BrandFragment
import com.example.risingtest.src.main.home.recommend.RecommendFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

data class AdArrayList(val position: String,val img: Int)
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    var adArrayList = ArrayList<AdArrayList>()
    private lateinit var ad_viewPager: HomeAdViewPagerAdapter
    private lateinit var handler : Handler
    private var currentPosition=0

    companion object {
        private lateinit var tab_recommend : RecommendFragment
        private lateinit var tab_brand : BrandFragment
        private lateinit var tabLayout : TabLayout
        private var NUM_PAGES : Int = 2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 광고 뷰페이저 쓰레드
        handler = Handler(Looper.getMainLooper()) {
            setPage()
            true
        }

        tab_recommend = RecommendFragment()
        tab_brand = BrandFragment()
        tabLayout = binding.tlMain

        val viewPager2: ViewPager2 = view.findViewById(R.id.vp_home_tab)
        viewPager2.adapter = HomeTabViewPagerAdapter(this)
        viewPager2.currentItem = 0

        val tabLayout: TabLayout = view.findViewById(R.id.tl_main)
        val tabLayoutMediator = TabLayoutMediator(
            tabLayout, viewPager2
        ) { tab, position ->
            if (position == 0) {
                tab.text = "추천상품"
            } else {
                tab.text = "브랜드"
            }
        }
        tabLayoutMediator.attach()
        adViewPager()
//        tab_fragment()

    }

    fun adViewPager(){


        adArrayList.add(AdArrayList("1",R.drawable.ic_ad_01))
        adArrayList.add(AdArrayList("2",R.drawable.ic_ad_02))
        adArrayList.add(AdArrayList("3",R.drawable.ic_ad_3))
        adArrayList.add(AdArrayList("4",R.drawable.ic_ad_04))

        ad_viewPager = HomeAdViewPagerAdapter(this.requireActivity(), adArrayList)
        binding.vpHomeAd.adapter = ad_viewPager

        // 뷰페이저 넘기는 쓰레드
        val thread = Thread(PagerRunnable())
        thread.start()
    }

    fun setPage() {
        if(currentPosition==4) {
            currentPosition=0
        }
        binding.vpHomeAd.setCurrentItem(currentPosition,true)
        currentPosition++
    }
    inner class PagerRunnable : Runnable {
        override fun run() {
            while(true){
                Thread.sleep(2000)
                handler.sendEmptyMessage(0)
            }
        }
    }

    private class HomeTabViewPagerAdapter(fragment: Fragment?) : FragmentStateAdapter(fragment!!) {
        override fun createFragment(position: Int): Fragment {
            return if (position == 0) {
                tab_recommend
            } else {
                tab_brand
            }
        }

        override fun getItemCount(): Int {
            return NUM_PAGES
        }
    }

//    private fun tab_fragment(){
//        tab_recommend = RecommendFragment()
//        tab_brand = BrandFragment()
//        tabLayout = binding.tlMain
//
//        childFragmentManager.beginTransaction().add(R.id.cl_fragment_main, tab_recommend).commit()
//
//        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                when(tab?.position){
//                    0 -> {
//                        replaceView(tab_recommend)
//                    }
//                    1 -> {
//                        replaceView(tab_brand)
//                    }
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//            }
//
//        })
//    }

//    private fun  replaceView(tab:Fragment) {
//        var selectedFragment : Fragment? = null
//        selectedFragment = tab
//        selectedFragment?.let {
//            childFragmentManager.beginTransaction()
//                .replace(R.id.cl_fragment_main,it).commit()
//        }
//    }
}