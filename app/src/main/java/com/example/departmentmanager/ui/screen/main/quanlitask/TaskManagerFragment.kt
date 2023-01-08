package com.example.departmentmanager.ui.screen.main.quanlitask

import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.activityViewModels
import com.example.departmentmanager.R
import com.example.departmentmanager.base.BaseFragment
import com.example.departmentmanager.data.model.ItemSpinner
import com.example.departmentmanager.databinding.LayoutTaskManagerFragmentBinding
import com.example.departmentmanager.ui.adapter.CRUDSpinnerAdapter
import com.example.departmentmanager.ui.adapter.ViewpagerAdapter
import com.example.departmentmanager.ui.screen.main.quanlitask.crud.AddTaskFragment
import com.example.departmentmanager.ui.screen.main.quanlitask.crud.EditTaskFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskManagerFragment : BaseFragment<LayoutTaskManagerFragmentBinding>() {
    private var listCRUD : ArrayList<ItemSpinner> = arrayListOf()
    private var adapterCRUD : CRUDSpinnerAdapter?= null
    private var addTaskFragment: AddTaskFragment?= null
    private var editTaskFragment: EditTaskFragment?= null
    private var adapterViewpager : ViewpagerAdapter?= null
    private val viewModel: TaskManagerViewModel by activityViewModels()
    override fun getContentLayout(): Int {
        return R.layout.layout_task_manager_fragment
    }

    override fun initView() {
        initData()
        initAdatper()
    }

    fun initData(){
        listCRUD.apply {
            add(ItemSpinner(title = "Thêm task"))
            add(ItemSpinner(title = "Sửa task"))
            add(ItemSpinner(title = "Xem task"))
        }
    }

    fun initAdatper(){
        adapterCRUD = CRUDSpinnerAdapter(listCRUD)
        binding.spinner.adapter = adapterCRUD

        addTaskFragment = AddTaskFragment()
        editTaskFragment = EditTaskFragment()
        adapterViewpager = ViewpagerAdapter(childFragmentManager, lifecycle)
        adapterViewpager?.addFragment(addTaskFragment!!)
        adapterViewpager?.addFragment(editTaskFragment!!)

        binding.viewpager.adapter = adapterViewpager
    }

    override fun initListener() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if(p2 == 0){
                    viewModel.getEmployeeOfDepartment()
                    binding.viewpager.currentItem = 0
                }
                else{
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