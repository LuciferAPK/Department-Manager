package com.example.departmentmanager.ui.screen.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import com.example.departmentmanager.R
import com.example.departmentmanager.base.BaseActivity
import com.example.departmentmanager.databinding.ActivitySplashBinding
import com.example.departmentmanager.navigation.NavigationManager
import com.example.departmentmanager.ui.screen.login.LoginActivity
import com.example.departmentmanager.utils.CoroutineExt
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun getContentLayout(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        CoroutineExt.runOnMainAfterDelay(2000) {
            navigationManager.gotoLoginActivityScreen()
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