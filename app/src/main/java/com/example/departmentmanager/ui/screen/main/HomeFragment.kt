package com.example.departmentmanager.ui.screen.main

import android.view.View
import com.example.departmentmanager.R
import com.example.departmentmanager.base.BaseFragment
import com.example.departmentmanager.databinding.FragmentHomeBinding
import com.example.departmentmanager.databinding.FragmentInfoBinding
import com.example.departmentmanager.ui.adapter.ItemFunctionAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var adapterFunction : ItemFunctionAdapter
    override fun getContentLayout(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        setupAdapter()
    }

    private fun setupAdapter(){
        adapterFunction = ItemFunctionAdapter(requireContext())
        binding.gvHome.adapter  = adapterFunction
    }
    override fun initListener() {

    }

    override fun observerLiveData() {

    }

    override fun getLayoutLoading(): View? {
        return null
    }

}