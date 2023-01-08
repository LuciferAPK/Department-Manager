package com.example.departmentmanager.ui.screen.main.quanlitask.crud

import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.departmentmanager.R
import com.example.departmentmanager.base.BaseFragment
import com.example.departmentmanager.base.Result
import com.example.departmentmanager.data.model.Employee
import com.example.departmentmanager.data.model.ItemSpinner
import com.example.departmentmanager.data.model.Task
import com.example.departmentmanager.databinding.LayoutAddTaskFragmentBinding
import com.example.departmentmanager.ui.adapter.CRUDSpinnerAdapter
import com.example.departmentmanager.ui.screen.main.quanlitask.TaskManagerViewModel
import com.example.departmentmanager.utils.DateTimeUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment : BaseFragment<LayoutAddTaskFragmentBinding>() {
    private var listEstimateItem : ArrayList<ItemSpinner> = arrayListOf()
    private var listEmployeeItem : ArrayList<ItemSpinner> = arrayListOf()
    private var listStatusItem : ArrayList<ItemSpinner> = arrayListOf()
    private var listEmployee : ArrayList<Employee> = arrayListOf()
    private var adapterEstimate : CRUDSpinnerAdapter?= null
    private var adapterStatus : CRUDSpinnerAdapter?= null
    private var adapterEmployee : CRUDSpinnerAdapter?= null
    private val viewModel : TaskManagerViewModel by activityViewModels()
    override fun getContentLayout(): Int {
        return R.layout.layout_add_task_fragment
    }

    override fun initView() {
        binding.tvCreatedTime.text = DateTimeUtils.millisecondsToDate(System.currentTimeMillis())
        initAdapter()
    }

    fun initAdapter(){
        listEstimateItem.apply {
            add(ItemSpinner(title = "Half day"))
            add(ItemSpinner(title = "1 day"))
            add(ItemSpinner(title = "2 days"))
            add(ItemSpinner(title = "Undefine"))
        }
        adapterEstimate = CRUDSpinnerAdapter(listEstimateItem)
        binding.spinnerChooseEstimate.adapter = adapterEstimate

        listStatusItem.apply {
            add(ItemSpinner(title = "To do"))
            add(ItemSpinner(title = "In progress"))
            add(ItemSpinner(title = "Ready for test"))
            add(ItemSpinner(title = "Reopened"))
            add(ItemSpinner(title = "In review"))
        }
        adapterStatus = CRUDSpinnerAdapter(listStatusItem)
        binding.spinnerChooseStatus.adapter = adapterStatus

        adapterEmployee = CRUDSpinnerAdapter(listEmployeeItem)
        binding.spinnerChooseEmployee.adapter = adapterEmployee
    }

    override fun initListener() {
        binding.btnAdd.setOnClickListener {
            var task = Task(
                title = binding.edtTitle.text.toString().trim(),
                details = binding.edtDetails.text.toString().trim(),
                createdTime = System.currentTimeMillis(),
                status = listStatusItem[0].title,
                assignTo = listEmployee[binding.spinnerChooseEmployee.selectedItemPosition]
            )
            viewModel.createTask(task)
        }
    }

    override fun observerLiveData() {
       viewModel.apply {
           employeeByDepartmentResult.observe(this@AddTaskFragment){result ->
               when(result){
                   is Result.InProgress ->{
                   }
                   is Result.Success ->{
                       listEmployee = result.data as ArrayList<Employee>
                       listEmployee.forEach{
                           listEmployeeItem.add(ItemSpinner(title = it.name))
                       }
                       adapterEmployee?.notifyDataSetChanged()
                   }
                   else ->{
                   }
               }

           }
           createdTaskResult.observe(this@AddTaskFragment){ result ->
               when(result){
                   is Result.InProgress ->{
                       binding.progressbar.visibility = View.VISIBLE
                   }
                   is Result.Success ->{
                       binding.progressbar.visibility = View.GONE
                       Toast.makeText(requireContext(),"Tạo thành công task", Toast.LENGTH_SHORT).show()
                   }
                   else ->{
                       binding.progressbar.visibility = View.GONE
                       Toast.makeText(requireContext(),"Error!!!", Toast.LENGTH_SHORT).show()
                   }
               }

           }
       }
    }

    override fun getLayoutLoading(): View? = null
}