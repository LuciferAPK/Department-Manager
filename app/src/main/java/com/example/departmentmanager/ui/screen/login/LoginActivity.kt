package com.example.departmentmanager.ui.screen.login

import android.view.View
import com.example.departmentmanager.R
import com.example.departmentmanager.base.BaseActivity
import com.example.departmentmanager.databinding.ActivityLoginBinding
import com.example.departmentmanager.navigation.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    @Inject
    lateinit var navigationManager: NavigationManager

    override fun getContentLayout(): Int {
        return R.layout.activity_login
    }

    override fun initView() {

    }

    override fun initListener() {
        binding.btnLogin.setOnClickListener {
            navigationManager.gotoMainActivityScreen()
        }
    }

    override fun observerLiveData() {

    }

    override fun getLayoutLoading(): View? {
        return null
    }
}