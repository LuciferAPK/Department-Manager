package com.example.departmentmanager.ui.screen.login

import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.departmentmanager.R
import com.example.departmentmanager.application.ApplicationContext.sessionContext
import com.example.departmentmanager.base.BaseActivity
import com.example.departmentmanager.base.Result
import com.example.departmentmanager.databinding.ActivityLoginBinding
import com.example.departmentmanager.navigation.NavigationManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    @Inject
    lateinit var navigationManager: NavigationManager
    private val viewModel : LoginViewModel by viewModels()

    override fun getContentLayout(): Int {
        return R.layout.activity_login
    }

    override fun initView() {

    }

    override fun initListener() {
        binding.btnLogin.setOnClickListener {
            binding.btnLogin.isEnabled = false
            viewModel.checkLogin(binding.tvUsername.text.toString().trim(), binding.tvPassword.text.toString().trim())
        }
    }

    override fun observerLiveData() {
        viewModel.apply {
            checkLoginEmployeeResult.observe(this@LoginActivity){ result ->
                when(result){
                    is Result.InProgress ->{
                        binding.progressbar.visibility = View.VISIBLE
                    }
                    is Result.Success ->{
                        binding.btnLogin.isEnabled = true
                        binding.progressbar.visibility = View.GONE
                        if(result.data.id == 0){
                            Toast.makeText(baseContext,"Sai thông tin đăng nhập", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            sessionContext.name = result.data.name.toString()
                            sessionContext.email = result.data.email.toString()
                            viewModel.saveEmployee(result.data)
                            navigationManager.gotoMainActivityScreen()
                        }
                    }
                    else ->{
                        binding.btnLogin.isEnabled = true
                        binding.progressbar.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun getLayoutLoading(): View? {
        return null
    }
}