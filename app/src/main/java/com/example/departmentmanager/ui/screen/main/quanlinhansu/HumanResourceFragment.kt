package com.example.departmentmanager.ui.screen.main.quanlinhansu

import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.activityViewModels
import com.example.departmentmanager.R
import com.example.departmentmanager.base.BaseFragment
import com.example.departmentmanager.data.model.ItemSpinner
import com.example.departmentmanager.databinding.LayoutHumanResourceFragmentBinding
import com.example.departmentmanager.ui.adapter.CRUDSpinnerAdapter
import com.example.departmentmanager.ui.adapter.ViewpagerAdapter
import com.example.departmentmanager.ui.screen.main.quanlinhansu.crud.AddEmployeeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HumanResourceFragment : BaseFragment<LayoutHumanResourceFragmentBinding>() {
    private var listCRUD : ArrayList<ItemSpinner> = arrayListOf()
    private var adapterCRUD : CRUDSpinnerAdapter ?= null
    private var addEmployeeFragment: AddEmployeeFragment ?= null
    private var adapterViewpager : ViewpagerAdapter ?= null
    private val viewModel : HumanResourceViewModel by activityViewModels()
    override fun getContentLayout(): Int {
        return R.layout.layout_human_resource_fragment
    }

    override fun initView() {
        initData()
        initAdapter()
    }

    private fun initAdapter(){
        adapterCRUD = CRUDSpinnerAdapter(listCRUD)
        binding.spinner.adapter = adapterCRUD

        addEmployeeFragment = AddEmployeeFragment.newInstance()
        adapterViewpager = ViewpagerAdapter(childFragmentManager, lifecycle)
        adapterViewpager?.addFragment(addEmployeeFragment!!)

        binding.viewpager.adapter = adapterViewpager
    }

    private fun initData(){
        listCRUD.apply {
            add(ItemSpinner(title = "Thêm nhân viên"))
            add(ItemSpinner(title = "Sửa nhân viên"))
            add(ItemSpinner(title = "Xóa nhân viên"))
            add(ItemSpinner(title = "Tìm nhân viên"))
        }
    }

    override fun initListener() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 0){
                    viewModel.getAllDepartment()
                    binding.viewpager.currentItem = 0
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    override fun observerLiveData() {

    }

    override fun getLayoutLoading(): View? {
        return null
    }
}