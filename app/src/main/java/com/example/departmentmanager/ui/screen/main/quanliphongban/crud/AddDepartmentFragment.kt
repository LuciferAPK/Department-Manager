package com.example.departmentmanager.ui.screen.main.quanliphongban.crud

import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.departmentmanager.R
import com.example.departmentmanager.base.BaseFragment
import com.example.departmentmanager.base.Result
import com.example.departmentmanager.data.model.Department
import com.example.departmentmanager.data.model.Employee
import com.example.departmentmanager.databinding.LayoutAddDepartmentFragmentBinding
import com.example.departmentmanager.ui.screen.main.quanliphongban.DepartmentViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddDepartmentFragment : BaseFragment<LayoutAddDepartmentFragmentBinding>() {
    private val viewModel: DepartmentViewModel by activityViewModels()
    override fun getContentLayout(): Int {
        return R.layout.layout_add_department_fragment
    }

    override fun initView() {
    }

    override fun initListener() {
        binding.btnAdd.setOnClickListener {
            val department = Department(
                name = binding.edtName.text.toString().trim(),
                function = binding.edtFunction.text.toString().trim(),
                description = binding.edtDescription.text.toString().toString(),
                capacity = binding.root.findViewById<RadioButton>(binding.rgCapacity.checkedRadioButtonId).text.toString().toInt(),
                createdTime = System.currentTimeMillis()
            )
            viewModel.createDepartment(department)
        }
    }

    override fun observerLiveData() {
        viewModel.apply {
            departmentCreatedResult.observe(this@AddDepartmentFragment){result ->
                when(result){
                    is Result.InProgress ->{
                        binding.progressbar.visibility = View.VISIBLE
                    }
                    is Result.Success ->{
                        binding.progressbar.visibility = View.GONE
                        Toast.makeText(requireContext(),"Tạo thành công phòng ban", Toast.LENGTH_SHORT).show()
                    }
                    else ->{
                        binding.progressbar.visibility = View.GONE
                        Toast.makeText(requireContext(),"Error!!!", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }

    override fun getLayoutLoading(): View? {
        return null
    }
}