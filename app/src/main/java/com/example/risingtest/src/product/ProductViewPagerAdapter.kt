package com.example.risingtest.src.product

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.risingtest.R
import com.example.risingtest.databinding.RvMainRecoomendItemBinding
import com.example.risingtest.databinding.ViewPagerHomeAdBinding
import com.example.risingtest.databinding.ViewPagerLoginBinding
import com.example.risingtest.databinding.ViewPagerProductBinding
import com.example.risingtest.src.login.AdArrayList
import com.example.risingtest.src.main.home.recommend.ProductData

class ProductViewPagerAdapter(context: Context, private val productImg: ArrayList<ProductImg>) : PagerAdapter() {

    private lateinit var binding : ViewPagerProductBinding
    private val inflater =  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    var product_img = ArrayList<ProductImg>()

    //position에 해당하는 페이지 생성
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        binding = ViewPagerProductBinding.inflate(inflater, container, false)
        Log.d("이미지 사진 URL", productImg[position].toString())

        Glide
            .with(binding.ivProductImg.context)
            .load(productImg[position])
            .into(binding.ivProductImg)

        container.addView(binding.root)

        return binding.root
    }

    //position에 위치한 페이지 제거
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }


    //사용가능한 뷰 개수 리턴
    override fun getCount(): Int {
        return productImg.size
    }

    //페이지뷰가 특정 키 객체(key object)와 연관 되는지 여부
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view==`object`)
    }

}

