package com.example.risingtest.src.main.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.risingtest.R
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.FragmentHomeBinding
import com.example.risingtest.src.MainActivity
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

        val pagerAdapter = HomeTabViewPagerAdapter(parentFragmentManager)
        val pager = view.findViewById<ViewPager>(R.id.vp_home_tab)
        pager.adapter = pagerAdapter
        val tab = view.findViewById<TabLayout>(R.id.tl_main)
        tab.setupWithViewPager(pager)

        adViewPager()
        toolbar(view)


//        val viewPager2: ViewPager2 = view.findViewById(R.id.vp_home_tab)
//        viewPager2.adapter = HomeTabViewPagerAdapter(this)
//        viewPager2.currentItem = 0
//
//        val tabLayout: TabLayout = view.findViewById(R.id.tl_main)
//        val tabLayoutMediator = TabLayoutMediator(
//            tabLayout, viewPager2
//        ) { tab, position ->
//            if (position == 0) {
//                tab.text = "추천상품"
//            } else {
//                tab.text = "브랜드"
//            }
//        }
//        tabLayoutMediator.attach()
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

//    //액션버튼 메뉴 액션바에 집어 넣기
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.toolbar_main_menu, menu)
//        return true
//    }

    fun toolbar(view: View){
        val toolbar: Toolbar = view.findViewById(R.id.toolbar) // 상단바
        toolbar.inflateMenu(R.menu.toolbar_main_menu) // 메뉴xml과 상단바 연결 (프래그먼트xml에서 연결했으면 안해도 됨) //
    // 상단바 메뉴 클릭시
        toolbar.setOnMenuItemClickListener{ when(it.itemId) {
            R.id.all -> {
                startActivity(Intent(context, MainActivity::class.java))
                true
            }
            R.id.search -> {
                startActivity(Intent(context, MainActivity::class.java))
                true
            }
            R.id.alarm -> {
                startActivity(Intent(context, MainActivity::class.java))
                true
            }
            else -> false
        }
        }

    }

}