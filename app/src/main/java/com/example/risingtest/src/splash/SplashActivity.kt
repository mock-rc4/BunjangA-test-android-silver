package com.example.risingtest.src.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.content.ContextCompat.startActivity
import com.example.risingtest.config.ApplicationClass
import com.example.risingtest.config.BaseActivity
import com.example.risingtest.databinding.ActivitySplashBinding
import com.example.risingtest.src.MainActivity
import com.example.risingtest.src.login.LoginActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val jwtToken : String? = ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN,null)


        Handler(Looper.getMainLooper()).postDelayed({

            if(jwtToken.isNullOrEmpty()){
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

//            startActivity(Intent(this, LoginActivity::class.java))
//            finish()
        }, 1500)
    }
}