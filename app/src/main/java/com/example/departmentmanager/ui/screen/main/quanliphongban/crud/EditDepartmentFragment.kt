package com.example.departmentmanager.ui.screen.main.quanliphongban.crud

import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.activityViewModels
import com.example.departmentmanager.R
import com.example.departmentmanager.base.BaseFragment
import com.example.departmentmanager.base.Result
import com.example.departmentmanager.data.model.Department
import com.example.departmentmanager.data.model.ItemSpinner
import com.example.departmentmanager.databinding.LayoutEditDepartmentFragmentBinding
import com.example.departmentmanager.ui.adapter.CRUDSpinnerAdapter
import com.example.departmentmanager.ui.screen.main.quanliphongban.DepartmentViewModel

class EditDepartmentFragment : BaseFragment<LayoutEditDepartmentFragmentBinding>(){
    private val viewModel : DepartmentViewModel by activityViewModels()
    lateinit var spinnerAdapterDepartment : CRUDSpinnerAdapter
    private val listItemSpinnerDepartment: ArrayList<ItemSpinner> = arrayListOf()
    private var currentDepartment : Department ?= null
    private var listDepartment: ArrayList<Department> = arrayListOf()
    override fun getContentLayout(): Int {
        return R.layout.layout_edit_department_fragment
    }

    override fun initView() {
        initAdapter()
    }

    fun initAdapter(){
        spinnerAdapterDepartment = CRUDSpinnerAdapter(listItemSpinnerDepartment)
        binding.spinnerChooseDepartment.adapter = spinnerAdapterDepartment
    }

    fun initData(){
        binding.edtName.setText(currentDepartment?.name)
        binding.edtFunction.setText(currentDepartment?.function)
        binding.edtDescription.setText(currentDepartment?.description)
        when(currentDepartment?.capacity){
            5 ->{
                binding.rb5.isChecked = true
            }
            7 -> {
                binding.rb7.isChecked = true
            }
            else -> binding.rb15.isChecked = true
        }
    }

    override fun initListener() {
        binding.spinnerChooseDepartment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                currentDepartment = listDepartment[p2]
                initData()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    override fun observerLiveData() {
        viewModel.apply {
            listDepartmentResults.observe(this@EditDepartmentFragment){result ->
                when(result){
                    is Result.InProgress ->{
                    }
                    is Result.Success ->{
                        listItemSpinnerDepartment.clear()
                        listDepartment = result.data as ArrayList<Department>
                        result.data.forEach{
                            listItemSpinnerDepartment.add(ItemSpinner(title = it.name))
                        }
                        spinnerAdapterDepartment.notifyDataSetChanged()
                    }
                    else ->{
                    }
                }

            }
        }
    }

    override fun getLayoutLoading(): View? {
        return null
    }
}