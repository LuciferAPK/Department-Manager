package com.example.departmentmanager.ui.screen.main

import android.view.View
import androidx.fragment.app.viewModels
import com.example.departmentmanager.R
import com.example.departmentmanager.base.BaseFragment
import com.example.departmentmanager.data.model.Employee
import com.example.departmentmanager.databinding.FragmentHomeBinding
import com.example.departmentmanager.databinding.FragmentInfoBinding
import com.example.departmentmanager.navigation.NavigationManager
import com.example.departmentmanager.ui.adapter.ItemFunctionAdapter
import com.example.departmentmanager.ui.screen.main.quanlinhansu.HumanResourceViewModel
import com.example.departmentmanager.ui.screen.main.quanlitask.TaskManagerViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var adapterFunction : ItemFunctionAdapter
    private val viewModel: HumanResourceViewModel by viewModels()
    var employee: Employee ?= null

    @Inject
    lateinit var navigationManager: NavigationManager
    override fun getContentLayout(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        employee = viewModel.getEmployee()
        setupAdapter()
    }

    private fun setupAdapter(){
        adapterFunction = ItemFunctionAdapter(requireContext(), onClickItem = { position ->
            run {
                when (position) {
                    0 -> {
                        navigationManager.gotoHumanResourceFragment(parentFragmentManager)
                    }
                    1 -> {
                        navigationManager.gotoDepartmentManagerFragment(parentFragmentManager)
                    }
                    2 -> {
                        navigationManager.gotoTaskManagerFragment(parentFragmentManager)
                    }
                }
            }

        })
        adapterFunction.setEmployee(employee)
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