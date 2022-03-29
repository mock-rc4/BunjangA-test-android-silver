package com.example.risingtest.src.deliveryToBuy

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.risingtest.R
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityDeliveryToBuyBinding
import com.example.risingtest.src.MainActivity
import com.example.risingtest.src.deliveryToBuy.models.DeliveryInfoResponse
import com.example.risingtest.src.deliveryToBuy.models.DeliveryRequest
import com.example.risingtest.src.register.RegisterService
import com.example.risingtest.src.register.models.PostSignUpRequest
import com.github.ybq.android.spinkit.animation.AnimationUtils.start


class DeliveryToBuyActivity : BaseActivity<ActivityDeliveryToBuyBinding>(ActivityDeliveryToBuyBinding::inflate),
    DeliveryToBuyActivityView {

    private var animationDrawable: AnimationDrawable? = null

    private var totalPaymentAmount : String = ""
    private var address : String = ""
    private var productIdx : String = ""
    private var safetyTax : String = ""
    private var transactionMethod : String = ""
    private var point : String = ""
    private var paymentMethod : String = "1"
    private var productName : String = ""
    private var productImg : String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        coinAni()
        PayWayBtnCheck()
        getInfo()

        binding.btnPayment.setOnClickListener {

            val postRequest = DeliveryRequest(productIdx = productIdx, totalPaymentAmount = totalPaymentAmount,
                address = address, safetyTax = safetyTax,
                transactionMethod = transactionMethod, point = point, paymentMethod = paymentMethod )
            showLoadingDialog(this)
            DeliveryService(this).tryPostDelivery(postRequest, productIdx)
        }
    }

    // 코인 돌아가는 애니메이션
    fun coinAni(){

        animationDrawable = binding.ivAnimationCoin.background as AnimationDrawable
        animationDrawable!!.start()
    }

    // 결제수단 클릭했을 경우
    @SuppressLint("ResourceAsColor")
    fun PayWayBtnCheck(){
        binding.rbtnBunjangPay.setOnClickListener {
            binding.tvBunjangPay.setTextColor(R.color.black)
            binding.tvOtherPay.setTextColor(R.color.dark_gray)
            binding.tvRegisterPayWay.setTextColor(R.color.light_light_light_light_gray)
            binding.rbtnOtherPay.isChecked=false
        }

        binding.rbtnOtherPay.setOnClickListener {
            binding.tvBunjangPay.setTextColor(R.color.dark_gray)
            binding.tvOtherPay.setTextColor(R.color.black)
            binding.tvRegisterPayWay.setTextColor(R.color.light_gray)
            binding.rbtnBunjangPay.isChecked=false
        }
    }

    // 상품 정보 가져오기
    fun getInfo(){
        if (intent.hasExtra("productIdx")) {
            productIdx = intent.getStringExtra("productIdx").toString()
            totalPaymentAmount = intent.getStringExtra("totalPaymentAmount").toString()
            address = intent.getStringExtra("address").toString()
            safetyTax = intent.getStringExtra("safeyTax").toString()
            transactionMethod = intent.getStringExtra("transactionMethod").toString()
            productName = intent.getStringExtra("productName").toString()
            productImg = intent.getStringExtra("productImg").toString()
        }
        else {
            Toast.makeText(this, "이름값이 없습니다!", Toast.LENGTH_SHORT).show()
        }

        binding.tvProductName.text = productName
        binding.tvProductPrice.text = totalPaymentAmount
        Glide
            .with(binding.ivProductImg.context)
            .load(productImg)
            .into(binding.ivProductImg)

    }

    fun payment(){

        Log.d("gkgk","gkgk")
        binding.btnPayment.setOnClickListener {

            val postRequest = DeliveryRequest(productIdx = productIdx, totalPaymentAmount = totalPaymentAmount,
                address = address, safetyTax = safetyTax,
                transactionMethod = transactionMethod, point = point, paymentMethod = paymentMethod )
            showLoadingDialog(this)
            DeliveryService(this).tryPostDelivery(postRequest, productIdx)
        }
    }

//    fun RegisterBtnClick(){
//        binding.btnRegister.setOnClickListener {
//            shopName = binding.edtStoreName.text.toString()
//
//            val postRequest = PostSignUpRequest(userName = userName, userBirth = userBirth,
//                phoneNumber = phoneNumber, userPwd = userPwd, shopName = shopName)
//            showLoadingDialog(this)
//            RegisterService(this).tryPostSignUp(postRequest)
//        }
//    }

    override fun onPostPaySuccess(response: DeliveryInfoResponse) {
        if(response.code==1000){
            showCustomToast("결제가 되었습니다.")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPostPayFailure(message: String) {
    }
}
