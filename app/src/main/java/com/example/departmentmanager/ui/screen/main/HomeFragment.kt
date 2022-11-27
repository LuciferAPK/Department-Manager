package com.example.departmentmanager.ui.screen.main

import android.view.View
import com.example.departmentmanager.R
import com.example.departmentmanager.base.BaseFragment
import com.example.departmentmanager.databinding.FragmentHomeBinding
import com.example.departmentmanager.databinding.FragmentInfoBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun getContentLayout(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {

    }

    override fun initListener() {

    }

    override fun observerLiveData() {

    }

    override fun getLayoutLoading(): View? {
        return null
    }

}