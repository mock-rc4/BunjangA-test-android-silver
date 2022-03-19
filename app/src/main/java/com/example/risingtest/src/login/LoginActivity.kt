package com.example.risingtest.src.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.viewpager.widget.ViewPager
import com.example.risingtest.R
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityLoginBinding
import com.example.risingtest.databinding.ActivityMainBinding

data class AdArrayList(val mention: Int, val mention_detail : String, val img: Int)
class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    var adArrayList = ArrayList<AdArrayList>()
    private var currentPosition=0
    private lateinit var handler : Handler
    private lateinit var login_viewPager: LoginViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 뷰페이저 바꾸기
        handler = Handler(Looper.getMainLooper()) {
            setPage()
            indicator()
            true
        }

        // 뷰페이저 설정
        setViewPager()

        // 뷰페이저 넘기는 쓰레드
        val thread = Thread(PagerRunnable())
        thread.start()

    }

    fun setPage() {
        if(currentPosition==4) {
            currentPosition=0
        }
        binding.vpLoginMent.setCurrentItem(currentPosition,true)
        currentPosition++
    }

    fun setViewPager(){
        adArrayList.add(AdArrayList(R.drawable.login_text,"요즘 유행하는 메이저 취향부터 \n 나만 알고싶은 마이너 취향까지",R.drawable.splash))
        adArrayList.add(AdArrayList(R.drawable.login_text_02,"번개톡, 번개페이로\n 거래의 시작부터 끝까지 안전하게",R.drawable.ic_security))
        adArrayList.add(AdArrayList(R.drawable.login_text_03,"포장택배 서비스로\n 픽업/포장/배송을 한번에",R.drawable.ic_box))
        adArrayList.add(AdArrayList(R.drawable.login_text_04,"지금 바로 번개장터에서\n 당신의 취향에 맞는 아이템들을 찾아보세요!",R.drawable.ic_heart))

        login_viewPager = LoginViewPagerAdapter(this, adArrayList)
        binding.vpLoginMent.adapter = login_viewPager
    }

    fun indicator() {
        binding.vpLoginMent.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                binding.ivIndicatorLogin01.setImageDrawable(getDrawable(R.drawable.shape_circle_gray))
                binding.ivIndicatorLogin02.setImageDrawable(getDrawable(R.drawable.shape_circle_gray))
                binding.ivIndicatorLogin03.setImageDrawable(getDrawable(R.drawable.shape_circle_gray))
                binding.ivIndicatorLogin04.setImageDrawable(getDrawable(R.drawable.shape_circle_gray))

                when(p0){
                    0 -> binding.ivIndicatorLogin01.setImageDrawable(getDrawable(R.drawable.shape_circle_black))
                    1 -> binding.ivIndicatorLogin02.setImageDrawable(getDrawable(R.drawable.shape_circle_black))
                    2 -> binding.ivIndicatorLogin03.setImageDrawable(getDrawable(R.drawable.shape_circle_black))
                    3 -> binding.ivIndicatorLogin04.setImageDrawable(getDrawable(R.drawable.shape_circle_black))
                }
            }
        })
    }

    inner class PagerRunnable : Runnable {
        override fun run() {
            while(true){
                Thread.sleep(3000)
                handler.sendEmptyMessage(0)
            }
        }
    }
}