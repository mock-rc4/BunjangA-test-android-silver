package com.example.risingtest.src.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityRegisterBinding
import com.example.risingtest.src.MainActivity
import com.example.risingtest.src.passwd.PasswordService
import com.example.risingtest.src.passwd.models.PostLoginRequest
import com.example.risingtest.src.register.models.PostSignUpRequest
import com.example.risingtest.src.register.models.SignUpResponse

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate), RegisterActivityView {

    private lateinit var userName : String
    private lateinit var userBirth : String
    private lateinit var phoneNumber : String
    private lateinit var userPwd : String
    private lateinit var shopName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getInfo()
        RegisterBtnClick()
        editTextListner()

        // 뒤로 가기 버튼 눌렀을 때
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    // 유저 정보 가져오기
    fun getInfo(){
        if (intent.hasExtra("userName")) {
            userName = intent.getStringExtra("userName").toString()
            userBirth = intent.getStringExtra("userBirth").toString()
            phoneNumber = intent.getStringExtra("phoneNumber").toString()
            userPwd = intent.getStringExtra("userPwd").toString()
        }
        else {
            Toast.makeText(this, "이름값이 없습니다!", Toast.LENGTH_SHORT).show()
        }
    }

    // 회원가입하기
    fun RegisterBtnClick(){
        binding.btnRegister.setOnClickListener {
            shopName = binding.edtStoreName.text.toString()

            val postRequest = PostSignUpRequest(userName = userName, userBirth = userBirth,
                phoneNumber = phoneNumber, userPwd = userPwd, shopName = shopName)
            showLoadingDialog(this)
            RegisterService(this).tryPostSignUp(postRequest)
        }
    }

    // 인증번호 입력할 때
    fun editTextListner() {
        binding.edtStoreName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // 입력하기 전에 조치
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // 입력난에 변화가 있을 시 조치
                if(binding.edtStoreName.length()<1){
                    binding.btnRegister.isClickable = false
                    binding.btnRegister.isEnabled = false
                }else {
                    binding.btnRegister.isEnabled=true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                // 입력 후 조치
            }

        })

    }

    override fun onPostSignUpSuccess(response: SignUpResponse) {
        dismissLoadingDialog()
        if(response.code==1000){
            showCustomToast("회원가입에 성공하였습니다.")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPostSignUpFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")
    }

}