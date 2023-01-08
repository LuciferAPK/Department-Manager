package com.example.departmentmanager.ui.screen.main.quanliphongban

import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.activityViewModels
import com.example.departmentmanager.R
import com.example.departmentmanager.base.BaseFragment
import com.example.departmentmanager.data.model.ItemSpinner
import com.example.departmentmanager.databinding.LayoutDepartmentManagerFragmentBinding
import com.example.departmentmanager.ui.adapter.CRUDSpinnerAdapter
import com.example.departmentmanager.ui.adapter.ViewpagerAdapter
import com.example.departmentmanager.ui.screen.main.quanlinhansu.crud.AddEmployeeFragment
import com.example.departmentmanager.ui.screen.main.quanliphongban.crud.AddDepartmentFragment
import com.example.departmentmanager.ui.screen.main.quanliphongban.crud.EditDepartmentFragment

class DepartmentManagerFragment : BaseFragment<LayoutDepartmentManagerFragmentBinding>() {
    private var listCRUD : ArrayList<ItemSpinner> = arrayListOf()
    private var adapterCRUD : CRUDSpinnerAdapter?= null
    private var addDepartmentFragment: AddDepartmentFragment?= null
    private var editDepartmentFragment: EditDepartmentFragment?= null
    private var adapterViewpager : ViewpagerAdapter?= null
    private val viewModel: DepartmentViewModel by activityViewModels()
    override fun getContentLayout(): Int {
        return R.layout.layout_department_manager_fragment
    }

    override fun initView() {
        initData()
        initAdapter()
    }

    fun initAdapter(){

        adapterCRUD = CRUDSpinnerAdapter(listCRUD)
        binding.spinner.adapter = adapterCRUD

        addDepartmentFragment = AddDepartmentFragment()
        editDepartmentFragment = EditDepartmentFragment()
        adapterViewpager = ViewpagerAdapter(childFragmentManager, lifecycle)
        adapterViewpager?.addFragment(addDepartmentFragment!!)
        adapterViewpager?.addFragment(editDepartmentFragment!!)

        binding.viewpager.adapter = adapterViewpager
    }

    fun initData(){
        listCRUD.apply {
            add(ItemSpinner(title = "Thêm phòng ban"))
            add(ItemSpinner(title = "Sửa phòng ban"))
            add(ItemSpinner(title = "Xóa phòng ban"))
        }
    }
    override fun initListener() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 0){
                    binding.viewpager.currentItem = 0
                }else{
                    viewModel.getAllDepartment()
                    binding.viewpager.currentItem = 1
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