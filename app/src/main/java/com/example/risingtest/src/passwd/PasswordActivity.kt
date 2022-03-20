package com.example.risingtest.src.passwd

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.risingtest.R
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityPasswordBinding
import com.example.risingtest.src.MainActivity
import com.example.risingtest.src.login.PhoneLoginActivity
import java.util.*

class PasswordActivity : BaseActivity<ActivityPasswordBinding>(ActivityPasswordBinding::inflate) {

    private lateinit var timer : Timer
    private lateinit var timerTask : TimerTask

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        timer = Timer()
        //타이머
//        TimerTask()

        editTextListner()
        showKeyboard()

        // 뒤로가기 버튼 눌렀을 때
        binding.ivBack.setOnClickListener {
            val intent = Intent(this, PhoneLoginActivity::class.java)
            startActivity(intent)
            finish()
        }
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

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}