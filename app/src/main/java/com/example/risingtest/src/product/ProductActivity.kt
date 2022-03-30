package com.example.risingtest.src.product

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bumptech.glide.Glide
import com.example.risingtest.R
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityProductBinding
import com.example.risingtest.src.deliveryToBuy.DeliveryService
import com.example.risingtest.src.deliveryToBuy.models.DeliveryRequest
import com.example.risingtest.src.product.models.ProductResponse
import com.example.risingtest.src.product.models.ZzimRequest
import com.example.risingtest.src.product.models.ZzimResponse
import com.google.android.flexbox.FlexboxLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.roundToInt


data class ProductImg(val img: String)
data class ProductInfo(val productIdx : String, val totalPaymentAmount : String,
                       val address : String, val safetyTax : String,
                       val transactionMethod : String, var productName : String, val productImg :String)

class ProductActivity : BaseActivity<ActivityProductBinding>(ActivityProductBinding::inflate), ProductActivityView {

    val product_img = mutableListOf<String>()
    var productInfo = ArrayList<ProductInfo>()

    private lateinit var product_viewPager: ProductViewPager
    private var product_img_size : Int = product_img.size

    private var totalPaymentAmount : String = ""
    private var address : String = ""
    private var productIdx : Int = 0
    private var safetyTax : String = ""
    private var transactionMethod : String = ""
    private var productName : String =""
    private var productImg : String = ""
    private var userIdx : String = "44"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        toolbar()
        toolbarScroll()
        productScroll()
        addTag()
        payBtn()
        zzim()
       // zzimStatus()

        // 상품 idx 가져오기
        productIdx = intent.getSerializableExtra("idx") as Int

        ProductService(this).tryGetProductInfoBooks(productIdx)
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

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_product_menu, menu)
        return true
    }


    // 인디케이터
//    fun indicator() {
//        binding.vpProductImg.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
//            override fun onPageScrollStateChanged(p0: Int) {
//            }
//
//            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
//            }
//
//            override fun onPageSelected(p0: Int) {
//                binding.ciProductImg.selectDot(p0)
//            }
//        })
//        //init indicator
//        binding.ciProductImg.createDotPanel(product_img.size, R.drawable.shape_circle_gray, R.drawable.shape_rect_red,0)
//    }

    override fun onGetProductInfoSuccess(response: ProductResponse) {

        binding.tvProductPrice.text = response.result?.price.toString()
        binding.tvProductPriceSmall.text = response.result?.price.toString()
        binding.tvProductName.text = response.result?.productName.toString()
        productName=response.result?.productName.toString()
        binding.tvProductNameSmall.text = response.result?.productName.toString()
        binding.tvViewCount.text = response.result?.viewCount.toString()
        binding.tvZzimCount.text = response.result?.likeCount.toString()

        binding.tvAddress.text = response.result?.directtrans.toString()
        binding.tvProductCount.text = response.result?.amount.toString()+"개"
        binding.tvProductWriting.text = response.result?.productDesc.toString()
        binding.tvCategoryName.text = response.result?.categoryName.toString()
//        binding.tvBrandTime.text = response.result?.createAt.toString()
//        binding.tvCountReview.text = response.result?.reviewCount.toString()

        Log.d("태그", response.result?.tag.toString())

        if(response.result?.saftyPay ==1){
            binding.tvSafety.visibility=View.VISIBLE
        }
        response.result?.directtrans?.let { binding.tvAddress.setText(it) }
        if(response.result?.productCondition==1) {
            binding.tvProductNewOrOld.text = "중고"
        }else {
            binding.tvProductNewOrOld.text = "새상품"
        }
        if(response.result?.includeFee==1){
            binding.tvProductDeliveryFee.text = "배송비포함"
            binding.tvProductDeliveryFeeSmall.text = "배송비포함"
        }else {
            binding.tvProductDeliveryFee.text = "배송비별도"
            binding.tvProductDeliveryFeeSmall.text = "배송비별도"
        }

        totalPaymentAmount = response.result?.price.toString()
        address = response.result?.directtrans.toString()
        safetyTax = "0"
        transactionMethod = response.result?.includeFee.toString()!!   // 택배비 포함

        binding.tvShopName.text = response.result?.shopName.toString()
        binding.tvStoreNameSmall.text = response.result?.shopName.toString()
        binding.tvFollowCount.text = response.result?.follower.toString()

        if(response.result?.myLike==1){
            !binding.cbProductZzim.isChecked
        }else {
            binding.cbProductZzim.isChecked
        }

        var str_arr = response.result?.imageUrl?.split(",")

        var i = 1
        if (str_arr != null) {
            for (data in str_arr) {
                if(i==1){
                    productImg = data
                }
                i++
            }
        }

        val imgUrl = response.result?.imageUrl?.split(",")
        if (imgUrl != null) {
            product_img.addAll(imgUrl)
        }

        val img_string=imgUrl?.get(0)

        Glide
            .with(this)
            .load(img_string)
            .into(binding.ivProductImgSmall)


        if(response.result?.tag.isNullOrEmpty()){
            val temptag = "태그가 없습니다."
            binding.flexBoxLayout.addchip(temptag)
        }else {
            //binding.flexBoxLayout.addchip(name)
//            binding.flexBoxLayout.addchip(name2)
//            binding.flexBoxLayout.addchip(name3)

        }

//        product_viewPager = ProductViewPagerAdapter(this, product_img)
        product_viewPager = ProductViewPager(this)
        val pager = binding.vpProductImg
        pager.adapter = product_viewPager
        product_img_size = product_img.size

        productInfo.add(ProductInfo(productIdx.toString(),totalPaymentAmount, address, safetyTax, transactionMethod, productName, productImg ))
    }
    override fun onGetProductInfoFailure(message: String) {
        Log.d("오류",message.toString())
    }

    override fun onPostZzimSuccess(response: ZzimResponse) {
        Log.d("code",response.code.toString())
        if(response.code==1000 && response.result?.status==1){
            showCustomToast("찜을 취소하였습니다.")
            !binding.cbProductZzim.isChecked
        }else if(response.code==1000 && response.result?.status==2) {
            showCustomToast("찜 목록에 추가하였습니다.")
            binding.cbProductZzim.isChecked
        }
        Log.d("status", response.result?.status.toString())
        ProductService(this).tryGetProductInfoBooks(productIdx)
    }

    override fun onPostZzimFailure(message: String) {
        Log.d("오류",message.toString())
    }

    fun zzimStatus() {
        binding.cbProductZzim.setOnClickListener {
            if(binding.cbProductZzim.isChecked){
                !binding.cbProductZzim.isChecked
            }else {
                binding.cbProductZzim.isChecked
            }
        }

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
            }

        })
    }

    fun addTag(){
        val name : String = "#샤넬 ㄴㄴㄴㄴㄴㄴ"
        val name2 : String = "#샤넬"
        val name3 : String = "#샤넬3432"
//        binding.flexBoxLayout.addchip(name)
//        binding.flexBoxLayout.addchip(name2)
//        binding.flexBoxLayout.addchip(name3)

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
//                val bottomDialogFragment = SelectBuyMethodBottomSheetDialog()
                val bottomDialogFragment = SelectBuyMethodBottomSheetDialog(productInfo)
                bottomDialogFragment.show(supportFragmentManager,"selectBottomView")
            }
        }
    }

    inner class ProductViewPager(fa : FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = product_img.size

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                in 0 until this.itemCount -> {
                    Log.d("produt_img",product_img[position].toString())
                    ProductImgFragment(product_img[position])
                }
                else -> ProductImgFragment(product_img[0])
            }
        }

    }

    // 찜했을 때
    fun zzim() {
        binding.cbProductZzim.setOnClickListener {
            val postRequest = ZzimRequest(userIdx = userIdx, productIdx = productIdx.toString())
            ProductService(this).tryPostZzim(postRequest)
        }
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
