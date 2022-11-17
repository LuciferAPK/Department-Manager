package com.example.departmentmanager.ui.screen.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.departmentmanager.R
import com.example.departmentmanager.base.BaseActivity
import com.example.departmentmanager.databinding.ActivitySplashBinding
import com.example.departmentmanager.ui.screen.main.MainActivity
import com.example.departmentmanager.utils.CoroutineExt

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun getContentLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        CoroutineExt.runOnMainAfterDelay(2000) {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }
    }

    override fun initListener() {

    }

    override fun observerLiveData() {

    }

    override fun getLayoutLoading(): View? {
        return null
    }
}