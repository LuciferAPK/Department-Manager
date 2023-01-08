package com.example.departmentmanager.ui.screen.main.profile

import android.view.View
import androidx.fragment.app.viewModels
import com.example.departmentmanager.R
import com.example.departmentmanager.base.BaseFragment
import com.example.departmentmanager.data.model.Employee
import com.example.departmentmanager.databinding.LayoutProfileFragmentBinding
import com.example.departmentmanager.ui.screen.main.quanlinhansu.HumanResourceViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<LayoutProfileFragmentBinding>() {
    private val viewModel : HumanResourceViewModel by viewModels()
    private var employee: Employee ?= null
    override fun getContentLayout(): Int {
        return R.layout.layout_profile_fragment
    }

    override fun initView() {
        initData()
    }

    fun initData(){
        employee = viewModel.getEmployee()
    }

    override fun initListener() {
    }

    override fun observerLiveData() {
    }

    override fun getLayoutLoading(): View? = null
}