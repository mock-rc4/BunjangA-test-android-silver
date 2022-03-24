package com.example.risingtest.src.product

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.risingtest.R
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityProductBinding
import com.example.risingtest.src.MainActivity


data class ProductImg(val img: Int)
class ProductActivity : BaseActivity<ActivityProductBinding>(ActivityProductBinding::inflate) {

    var product_img = ArrayList<ProductImg>()
    private lateinit var product_viewPager: ProductViewPagerAdapter
    private var product_img_size : Int = product_img.size
//    private var product_indicator_dot = ArrayList<ImageView>(product_img_size)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbar()
        setViewPager()
//        indicator()
//        product_img_size=product_img.size
        Log.d("왜그러저ㅣ?",product_img_size.toString())
//
//        for (i in 0 until product_img_size){
//            binding.llIndicator.addView(createIndicator())
//        }
    }

    private fun createIndicator(): View? {
        val indicator = ImageView(this)
        indicator.setBackgroundColor(this.getResources().getColor(R.color.light_light_gray))
        val lp : LinearLayout.LayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        indicator.layoutParams=lp
        return indicator
    }

    // 툴바
    fun toolbar(){
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar.inflateMenu(R.menu.toolbar_product_menu) // 메뉴xml과 상단바 연결 (프래그먼트xml에서 연결했으면 안해도 됨) //
        // 상단바 메뉴 클릭시
        toolbar.setOnMenuItemClickListener{ when(it.itemId) {
            R.id.search -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            R.id.alarm -> {
                startActivity(Intent(this, MainActivity::class.java))
                true
            }
            else -> false
        }
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_product_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search -> {
                Toast.makeText(applicationContext, "Search Click", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.share -> {
                Toast.makeText(applicationContext, "Option Click", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    // 상품이미지 뷰페이저
    fun setViewPager(){

        product_img.add(ProductImg(R.drawable.ic_product_img_sample))
        product_img.add(ProductImg(R.drawable.ic_brand_detail_product_name))
        product_img.add(ProductImg(R.drawable.ic_product_img_sample))
        product_img.add(ProductImg(R.drawable.ic_brand_detail_product_name))
        product_img.add(ProductImg(R.drawable.ic_brand_detail_product_name))
        product_img.add(ProductImg(R.drawable.ic_brand_detail_product_name))

        product_viewPager = ProductViewPagerAdapter(this, product_img)
        binding.vpProductImg.adapter = product_viewPager
        product_img_size = product_img.size

        indicator()
    }


    // 인디케이터
    fun indicator() {
        binding.vpProductImg.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                binding.ciProductImg.selectDot(p0)
            }
        })
        //init indicator
        binding.ciProductImg.createDotPanel(product_img.size, R.drawable.shape_circle_gray, R.drawable.shape_circle_red,0)
    }


    // 인디케이터 뷰 생성
//    fun crateIndicator() : View{
//        val indicator = ImageView(this)
//        indicator.setBackgroundColor(this.getResources().getColor(R.color.light_light_gray))
//        val lp : LinearLayout.LayoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        indicator.layoutParams=lp
//        return indicator
//    }

//    fun createDotPanel(layout: LinearLayout, count: Int) {
//        imgCircle = arrayOfNulls<ImageView>(count)
//        layout.removeAllViews()
//        for (i in 0 until count) {
//            imgCircle.get(i) = ImageView(this)
//            val params: LinearLayout.LayoutParams =
//                LinearLayout.LayoutParams(ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT)
//            params.leftMargin = 10
//            params.rightMargin = 10
//            params.gravity = Gravity.CENTER
//            imgCircle.get(i).setLayoutParams(params)
//            imgCircle.get(i).setImageResource(R.drawable.img_circle_indicator_default)
//            layout.addView(imgCircle.get(i))
//        }
//    }

//    fun selectIndicator(position:Int) {
//        for (i in 0 until product_img_size) {
//            if(i==position) {
//                product_indicator_dot.get(i)
//                    .setImageResource(R.drawable.shape_circle_red)
//            }else {
//                product_indicator_dot.get(i)
//                    .setImageResource(R.drawable.shape_circle_gray)
//            }
//        }
//    }
//
//    fun selectDot(position: Int) {
//        for (i in 0 until imgCircle.length) {
//            if (i == position) imgCircle.get(i)
//                .setImageResource(R.drawable.img_circle_indicator_selected) else imgCircle.get(i)
//                .setImageResource(R.drawable.img_circle_indicator_default)
//        }
//    }

}