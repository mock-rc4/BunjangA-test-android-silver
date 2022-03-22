package com.example.risingtest.src.passwd

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.risingtest.R
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityPasswordBinding
import com.example.risingtest.src.MainActivity
import com.example.risingtest.src.login.PhoneLoginActivity
import com.example.risingtest.src.passwd.models.LoginResponse
import com.example.risingtest.src.passwd.models.PostLoginRequest
import com.example.risingtest.src.register.RegisterActivity
import java.util.*

class PasswordActivity : BaseActivity<ActivityPasswordBinding>(ActivityPasswordBinding::inflate),PasswordActivityView {

    private lateinit var timer : Timer
    private lateinit var timerTask : TimerTask
    private lateinit var userName : String
    private lateinit var userBirth : String
    private lateinit var phoneNumber : String
    private lateinit var userPwd : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        timer = Timer()

        editTextListner()
        showKeyboard()
        loginBtnClick()
        getUserInfo()

        // 뒤로가기 버튼 눌렀을 때
        binding.ivBack.setOnClickListener {
            val intent = Intent(this, PhoneLoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        //타이머
//        TimerTask()
    }

    // 유저 정보 받아오기
    fun getUserInfo() {
        if (intent.hasExtra("userName")) {
            userName = intent.getStringExtra("userName").toString()
            userBirth = intent.getStringExtra("userBirth").toString()
            phoneNumber = intent.getStringExtra("phoneNumber").toString()
            binding.tvPhoneNumber.setText(phoneNumber)
        }
        else {
            Toast.makeText(this, "이름값이 없습니다!", Toast.LENGTH_SHORT).show()
        }
    }

    // 패스워드 치고 '다음' 눌렀을 때
    fun loginBtnClick(){
        binding.btnNext.setOnClickListener {
            userPwd = binding.edtNumber.text.toString()

            val postRequest = PostLoginRequest(userName = userName, userBirth = userBirth,
                phoneNumber = phoneNumber, userPwd = userPwd)
            showLoadingDialog(this)
            PasswordService(this).tryPostLogin(postRequest)
        }
    }

    // 키보드 바로 올리
    fun showKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun hideKeyborard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.edtNumber.windowToken, 0)
    }

    // 인증번호 입력할 때
    fun editTextListner() {
        binding.edtNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // 입력하기 전에 조치
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // 입력난에 변화가 있을 시 조치
                if(binding.edtNumber.length()<1){
                    binding.btnNext.isClickable = false
                    binding.btnNext.isEnabled = false
                }else {
                    // 이름 입력했을 때
//                    binding.btnRegisterCheck.isClickable = true
//                    binding.btnRegisterCheck.isEnabled = true
                    binding.btnNext.isEnabled=true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                // 입력 후 조치
            }

        })

        // 비밀번호 치고 다음으로 이동
//        binding.btnNext.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
    }

    override fun onPostUserSuccess(response: LoginResponse) {
        dismissLoadingDialog()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onPostUserFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("오류 : $message")

        // 유저가 없다면
        signUpUser()

        // 비밀번호 오류라면
        passwdFail()
    }

    // 비밀번호가 없으면 회원가입 창(상점명 설정)으로 이동
    fun signUpUser(){
        val intent = Intent(this, RegisterActivity::class.java)
        intent.putExtra("userName", userName)
        intent.putExtra("userBirth", userBirth)
        intent.putExtra("phoneNumber", phoneNumber)
        intent.putExtra("userName", userName)
        startActivity(intent)
    }

    // 비밀번호 오류났을 때
    fun passwdFail(){
        binding.tvNumberCheck.setText("인증번호가 올바르지 않습니다.")
        binding.tvNumberCheck.setTextColor(this.getResources().getColor(R.color.bunjang_color))
        binding.view.setBackgroundColor(this.getResources().getColor(R.color.bunjang_color))
    }
    //    private fun TimerTask() {
//        timerTask = object : TimerTask() {
//            var count=10
//            override fun run() {
//                binding.tvTimer.post(Runnable {
//                    if (count > 0) {
//                        binding.tvTimer.setText(count.toString())
//                    } else {
////                        val intent = Intent(applicationContext, PlaitingActivity::class.java)
////                        intent.putExtra("list",list)
////                        intent.putExtra("array_img",images)
////                        startActivity(intent)
////                        finish()
//                    }
//                    count--
//                })
//            }
//        }
//        timer.schedule(timerTask, 0, 1000)
//    }
}