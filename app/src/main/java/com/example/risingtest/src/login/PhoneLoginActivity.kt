package com.example.risingtest.src.login

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.example.risingtest.R
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivityPhoneLoginBinding
import com.example.risingtest.src.passwd.PasswordActivity


class PhoneLoginActivity : BaseActivity<ActivityPhoneLoginBinding>(ActivityPhoneLoginBinding::inflate) {

    private var telecome : String = ""
    private var isFinish : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 자동 키보드 올라오기
        showKeyboard()

        // 입력 이벤트
        editTextListner()
        edtListner()

        // 뒤로가기 버튼 눌렀을 때
        binding.ivBack.setOnClickListener {
            finish()
        }

        // 통신사 선택
//        showBottomSheet()
//        binding.tvSelectTelecom.text=telecome
//        binding.tvSelectTelecom.visibility=View.VISIBLE



    }

    fun showKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    fun hideKeyborard(){
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.edtPhoneNumber.windowToken, 0)
    }


//    fun showBottomSheet(){
//        binding.clSelectTelecom.setOnClickListener {
//            val bottomDialogFragment: PhoneLoginBottomSheetDialog = PhoneLoginBottomSheetDialog {
//                when (it) {
//                    0 -> telecome="SKT"
//                    1 -> telecome="KT"
//                    2 -> telecome="LG U+"
//                    3 -> telecome="SKT 알뜰폰"
//                    4 -> telecome="KT 알뜰폰"
//                    5 -> telecome="LG U+ 알뜰폰"
//                }
//            }
//            bottomDialogFragment.show(supportFragmentManager, "select_telecom")
//        }
//    }

    // 이름 입력할 때
    fun editTextListner() {
        binding.edtName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // 입력하기 전에 조치
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // 입력난에 변화가 있을 시 조치
                if(binding.edtName.length()<1){
                    binding.btnRegisterCheck.isClickable = false
                    binding.btnRegisterCheck.isEnabled = false
                }else {
                    // 이름 입력했을 때
//                    binding.btnRegisterCheck.isClickable = true
//                    binding.btnRegisterCheck.isEnabled = true
                    binding.btnRegisterCheck.isEnabled=true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                // 입력 후 조치
            }

        })

        binding.btnRegisterCheck.setOnClickListener {
                binding.llBirthday.visibility=View.VISIBLE
                binding.edtBirthday.requestFocus()
                binding.viewName.setBackgroundColor(this.getResources().getColor(R.color.light_light_gray))
                binding.tvMention.setText("생년월일을 \n입력해주세요")
                isFinish=true
        }
    }

    // 생년월일 입력할
    fun edtListner() {
        binding.edtBirthday.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnRegisterCheck.isClickable = false
                binding.btnRegisterCheck.setText("확인")
                binding.btnRegisterCheck.isEnabled=false

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.edtBirthday.length()==6){
                    binding.edtSex.requestFocus()
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                edtListnerBirthday()
            }

        })
    }

    // 주민번호 1,2, 입력할 때
    fun edtListnerBirthday(){
        binding.edtSex.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.edtSex.length()>0){
                    binding.llPhoneNumeber.visibility = View.VISIBLE
                    binding.edtPhoneNumber.requestFocus()
                    binding.tvMention.setText("휴대폰번호를 \n입력해주세요")
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                edtListnerPhoneNumber()
            }

        })
    }

    // 휴대폰 번호 입력할 때
    fun edtListnerPhoneNumber(){
        binding.edtPhoneNumber.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.edtPhoneNumber.length()==11){
                    binding.btnRegisterCheck.isClickable = true
                    binding.btnRegisterCheck.isEnabled = true
                    binding.tvMention.setText("입력한 정보를 \n확인해주세요")
                    hideKeyborard()
                    binding.btnRegisterCheck.isEnabled=true
                    isFinish=true
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        binding.btnRegisterCheck.setOnClickListener {
            if(isFinish){
                val intent = Intent(this, PasswordActivity::class.java)
                startActivity(intent)
                finish()
            }

        }
    }

}