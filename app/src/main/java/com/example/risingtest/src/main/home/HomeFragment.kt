package com.example.risingtest.src.main.home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.risingtest.R
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.FragmentHomeBinding
import com.example.risingtest.src.main.home.brand.BrandFragment
import com.example.risingtest.src.main.home.recommend.RecommendFragment
import com.example.risingtest.src.product.ProductActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import kotlin.math.abs
import kotlin.math.min

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
        private var scorll_x : Int = 0
        private var scroll_oldx : Int = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 광고 뷰페이저 쓰레드
//        handler = Handler(Looper.getMainLooper()) {
//            setPage()
//            true
//        }

//        Handler().postDelayed({
//            setPage()
//        },500)
//
//        CoroutineScope(Dispatchers.Main).launch {
//            setPage()
//        }
        handler=Handler(Looper.getMainLooper()){
            setPage()
            true
        }
//
//        handler = Handler()

        binding.ivZzim.setOnClickListener {
            val intent = Intent(this.requireActivity(), ProductActivity::class.java)
            startActivity(intent)
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
        scroll()
        toolbarScroll()
        setPage()
//        binding.seekBar.setProgress(40)


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

    // 메인화면 광고
    fun adViewPager(){

//        val recommendAdapter = HomeViewPagerAdapter(this)
//        recommendAdapter.addFragment(AdFragment())
//        recommendAdapter.addFragment(AdFragment2())
//        recommendAdapter.addFragment(AdFragment3())

//        val viewpager: ViewPager2 = binding.vpHomeAd
//        binding.vpHomeAd.adapter = recommendAdapter
//        binding.vpHomeAd.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        adArrayList.add(AdArrayList("1",R.drawable.ic_ad_01))
        adArrayList.add(AdArrayList("2",R.drawable.ic_ad_02))
        adArrayList.add(AdArrayList("3",R.drawable.ic_ad_3))
        adArrayList.add(AdArrayList("4",R.drawable.ic_ad_04))

        ad_viewPager = HomeAdViewPagerAdapter(this.requireActivity(), adArrayList)
        binding.vpHomeAd.adapter = ad_viewPager
        // 넘길 때 효과 제거
        val child = binding.vpHomeAd.getChildAt(0)
        (child as? RecyclerView)?.overScrollMode = View.OVER_SCROLL_NEVER

        // 뷰페이저 넘기는 쓰레드
        val thread = Thread(PagerRunnable())
        thread.start()

    }

    // 뷰페이저 position
    fun setPage() {
        if(currentPosition==4) {
            currentPosition=0
        }
        binding.vpHomeAd.setCurrentItem(currentPosition,true)
        currentPosition++
    }

    //2초 마다 페이지 넘기기
    inner class PagerRunnable:Runnable{
        override fun run() {
            while(true){
                try {
                    Thread.sleep(2000)
                    handler.sendEmptyMessage(0)
                } catch (e : InterruptedException){
                    Log.d("interupt", "interupt발생")
                }
            }
        }
    }
//
//    inner class PagerRunnable : Runnable {
//        override fun run() {
//            while(true){
//                Thread.sleep(2000)
//                handler.sendEmptyMessage(0)
//            }
//        }
//    }

//    //액션버튼 메뉴 액션바에 집어 넣기
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.toolbar_main_menu, menu)
//        return true
//    }

    // 메인 툴바
    fun toolbar(view: View){
        val toolbar: Toolbar = view.findViewById(R.id.toolbar) // 상단바
        toolbar.inflateMenu(R.menu.toolbar_main_menu) // 메뉴xml과 상단바 연결 (프래그먼트xml에서 연결했으면 안해도 됨) //
    // 상단바 메뉴 클릭시
//        toolbar.setOnMenuItemClickListener{ when(it.itemId) {
//            R.id.all -> {
//                startActivity(Intent(context, MainActivity::class.java))
//                true
//            }
//            R.id.search -> {
//                startActivity(Intent(context, MainActivity::class.java))
//                true
//            }
//            R.id.alarm -> {
//                startActivity(Intent(context, MainActivity::class.java))
//                true
//            }
//            else -> false
//        }
//        }

    }

    fun scroll(){
        binding.hsvHomeTable.setOnScrollChangeListener { p0, p1, p2, p3, p4 ->
            val view = p0.scrollBarSize
            Log.d("view",view.toString())
            if(p1>p3){
                // 오른쪽으로 스크롤 을 때
                scorll_x = p1
                scroll_oldx = p3
            }
            else if(p1<p3){
                // 왼쪽으로 스크롤 했을 때
                scorll_x = p1
                scroll_oldx = p3
            }
//            binding.seekBar.setProgress(40+scorll_x)
            if(scorll_x==11){
            }else {
                binding.scrollView.translationX = scorll_x.toFloat()
            }
        }
    }

    fun toolbarScroll(){
        binding.appbar.addOnOffsetChangedListener(object :
        AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                val alpha = min(abs(verticalOffset/2),255)
                binding.clToolbar.setBackgroundColor(Color.argb(alpha, 255,255,255))
                if(alpha > 255 /2){
                    binding.ivToolbarAlarm.setColorFilter(Color.argb(alpha, 0,0,0))
                    binding.ivToolbarAll.setColorFilter(Color.argb(alpha, 0,0,0))
                    binding.ivToolbarSearch.setColorFilter(Color.argb(alpha, 0,0,0))
                }else {
                    binding.ivToolbarAlarm.setColorFilter(Color.argb(alpha, 255,255,255))
                    binding.ivToolbarAll.setColorFilter(Color.argb(alpha, 255,255,255))
                    binding.ivToolbarSearch.setColorFilter(Color.argb(alpha, 255,255,255))
                }
            }

        })
    }


//    fun control_scroll(){
//        ObjectAnimator.ofFloat(view, "ScrollX", scroll_oldx, scroll_oldx).app
//    }

}