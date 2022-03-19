package com.example.risingtest.src.login

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.config.BaseFragment
import com.example.risingtest.databinding.ActivityPhoneLoginBinding

class PhoneLoginActivity : BaseActivity<ActivityPhoneLoginBinding>(ActivityPhoneLoginBinding::inflate) {

    private var telecome : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 통신사 선택
        showBottomSheet()

        binding.tvSelectTelecom.text=telecome
        binding.tvSelectTelecom.visibility=View.VISIBLE


    }

    fun showBottomSheet(){
        binding.clSelectTelecom.setOnClickListener {
            val bottomDialogFragment: PhoneLoginBottomSheetDialog = PhoneLoginBottomSheetDialog {
                when (it) {
                    0 -> telecome="SKT"
                    1 -> telecome="KT"
                    2 -> telecome="LG U+"
                    3 -> telecome="SKT 알뜰폰"
                    4 -> telecome="KT 알뜰폰"
                    5 -> telecome="LG U+ 알뜰폰"
                }
            }
            bottomDialogFragment.show(supportFragmentManager, "select_telecom")
        }
    }
}