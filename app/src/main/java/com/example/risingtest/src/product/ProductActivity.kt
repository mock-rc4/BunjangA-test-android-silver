package com.example.risingtest.src.product

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.risingtest.R
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityProductBinding
import com.example.risingtest.src.product.models.ProductResponse
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.roundToInt


data class ProductImg(val img: Int)
class ProductActivity : BaseActivity<ActivityProductBinding>(ActivityProductBinding::inflate), ProductActivityView {

    var product_img = ArrayList<ProductImg>()
    private var productIdx : Int = 0
    private lateinit var product_viewPager: ProductViewPagerAdapter
    private var product_img_size : Int = product_img.size
//    private var product_indicator_dot = ArrayList<ImageView>(product_img_size)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbar()
        setViewPager()
        toolbarScroll()
        productScroll()
        addTag()
        payBtn()

        // 상품 idx 가져오기
        productIdx = intent.getSerializableExtra("idx") as Int

        ProductService(this).tryGetProductInfoBooks(productIdx)
//        indicator()
//        product_img_size=product_img.size
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
//        toolbar.setOnMenuItemClickListener{ when(it.itemId) {
//            R.id.search -> {
//                startActivity(Intent(this, MainActivity::class.java))
//                true
//            }
//            R.id.alarm -> {
//                startActivity(Intent(this, MainActivity::class.java))
//                true
//            }
//            else -> false
//        }
//        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_product_menu, menu)
        return true
    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
////        return when (item.itemId) {
////            R.id.search -> {
////                Toast.makeText(applicationContext, "Search Click", Toast.LENGTH_SHORT).show()
////                true
////            }
////            R.id.share -> {
////                Toast.makeText(applicationContext, "Option Click", Toast.LENGTH_SHORT).show()
////                true
////            }
////            else -> super.onOptionsItemSelected(item)
////        }
//    }

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
        binding.ciProductImg.createDotPanel(product_img.size, R.drawable.shape_circle_gray, R.drawable.shape_rect_red,0)
    }

    override fun onGetProductInfoSuccess(response: ProductResponse) {
//        product_img.add(ProductImg(response.result))
        Log.d("결과",response.result.toString())


//        for (data in response.result.)
//        for (station in response.response?.body?.monitoringStations!!) {
//            near_station = station.stationName.toString()
//            add = station.addr.toString()
//        }
    }
    override fun onGetProductInfoFailure(message: String) {
        Log.d("오류",message.toString())
    }

    fun toolbarScroll(){
        binding.appbar.addOnOffsetChangedListener(object :
            AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                val alpha = min(abs(verticalOffset/3),255)
                binding.clToolbar.setBackgroundColor(Color.argb(alpha, 255,255,255))
                if(alpha > 255 /2){
                    binding.ivProductToolbarBack.setColorFilter(Color.argb(alpha, 0,0,0))
                    binding.ivProductToolbarSearch.setColorFilter(Color.argb(alpha, 0,0,0))
                    binding.ivProductToolbarShare.setColorFilter(Color.argb(alpha, 0,0,0))
                    binding.tvProductInfo.visibility=View.VISIBLE
                }else {
                    binding.ivProductToolbarBack.setColorFilter(Color.argb(alpha, 255,255,255))
                    binding.ivProductToolbarSearch.setColorFilter(Color.argb(alpha, 255,255,255))
                    binding.ivProductToolbarShare.setColorFilter(Color.argb(alpha, 255,255,255))
                    binding.tvProductInfo.visibility=View.INVISIBLE
                }
            }
        })
    }

    fun productScroll(){
        binding.appbar.addOnOffsetChangedListener(object :
        AppBarLayout.OnOffsetChangedListener {
            override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
                if(verticalOffset < -902){
                    binding.clProductToolbar.visibility=View.VISIBLE
                }else {
                    binding.clProductToolbar.visibility=View.GONE
                }
                Log.d("vieir",verticalOffset.toString())
            }

        })
    }

    fun addTag(){
        val name : String = "#샤넬 ㄴㄴㄴㄴㄴㄴ"
        val name2 : String = "#샤넬"
        val name3 : String = "#샤넬3432"
        binding.flexBoxLayout.addchip(name)
        binding.flexBoxLayout.addchip(name2)
        binding.flexBoxLayout.addchip(name3)

    }

    private fun FlexboxLayout.addchip(tag : String){
        val text = LayoutInflater.from(context).inflate(R.layout.view_tag,null) as TextView
        text.text = tag

        val layoutParams = ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT)
        layoutParams.rightMargin = dpToPx(20)

        addView(text, childCount-1, layoutParams)
    }

    private fun FlexboxLayout.getAllChips():List<Chip> {
        return (0 until childCount).mapNotNull { index ->
            getChildAt(index) as? Chip
        }
    }

    private fun FlexboxLayout.clearChips(){
        val chipViews = (0 until childCount).mapNotNull { index ->
            val view = getChildAt(index)
            if (view is Chip) view else null
        }
        chipViews.forEach{ removeView(it)}
    }

    fun Context.dpToPx(dp : Int) : Int
    = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics).roundToInt()

    // 번개페이 안전결제 클릭
    fun payBtn(){
        binding.tvBuyProduct.setOnClickListener {
            val bottomSheetView = layoutInflater.inflate(R.layout.fragment_buy_product_bottom_sheet, null)
            val bottomSheetDialog = BottomSheetDialog(this)
            bottomSheetDialog.setContentView(bottomSheetView)

            binding.tvBuyProduct.setOnClickListener {
                val bottomDialogFragment = SelectBuyMethodBottomSheetDialog()
                bottomDialogFragment.show(supportFragmentManager,"selectBottomView")
            }
        }
    }

//    fun scroll(){
//        binding.appbar.setOnScrollChangeListener { p0, p1, p2, p3, p4 ->
//            val view = p0.scrollBarSize
//            Log.d("view",view.toString())
//            if(p2>p4){
//                // 아래로 스크롤 할 때
////                HomeFragment.scorll_x = p1
////                HomeFragment.scroll_oldx = p3
//            }
//            else if(p2<p4){
//                // 위로 스크롤 했을 때
////                HomeFragment.scorll_x = p1
////                HomeFragment.scroll_oldx = p3
//            }
////            binding.seekBar.setProgress(40+scorll_x)
////            if(HomeFragment.scorll_x ==11){
////            }else {
////                binding.scrollView.translationX = HomeFragment.scorll_x.toFloat()
////            }
//        }
//    }


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
