package com.example.departmentmanager.ui.screen.main.quanlinhansu.crud

import android.app.DatePickerDialog
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.departmentmanager.R
import com.example.departmentmanager.base.BaseFragment
import com.example.departmentmanager.base.Result
import com.example.departmentmanager.data.model.Department
import com.example.departmentmanager.data.model.Employee
import com.example.departmentmanager.data.model.ItemSpinner
import com.example.departmentmanager.databinding.LayoutAddEmployeeFragmentBinding
import com.example.departmentmanager.ui.adapter.CRUDSpinnerAdapter
import com.example.departmentmanager.ui.screen.main.quanlinhansu.HumanResourceViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class AddEmployeeFragment : BaseFragment<LayoutAddEmployeeFragmentBinding>() {
    lateinit var spinnerAdapterDepartment : CRUDSpinnerAdapter
    lateinit var spinnerAdapterPosition : CRUDSpinnerAdapter
    private val listItemSpinnerDepartment: ArrayList<ItemSpinner> = arrayListOf()
    private var listDepartment: ArrayList<Department> = arrayListOf()
    private val listPosition: ArrayList<ItemSpinner> = arrayListOf()
    private var department : Department ?= null
    private val viewModel : HumanResourceViewModel by activityViewModels()
    override fun getContentLayout(): Int {
        return R.layout.layout_add_employee__fragment
    }

    companion object{
        fun newInstance() = AddEmployeeFragment()
    }

    override fun initView() {
        initAdapter()

    }

    private fun initAdapter(){
        listPosition.apply {
            add(ItemSpinner(title = "LEADER"))
            add(ItemSpinner(title = "STAFF"))
            add(ItemSpinner(title = "HR"))
            add(ItemSpinner(title = "CTO"))
            add(ItemSpinner(title = "MANAGER"))
            add(ItemSpinner(title = "INTERN"))
            add(ItemSpinner(title = "FRESHER"))
        }
        spinnerAdapterPosition = CRUDSpinnerAdapter(listPosition)
        binding.spinnerChoosePosition.adapter = spinnerAdapterPosition

        spinnerAdapterDepartment = CRUDSpinnerAdapter(listItemSpinnerDepartment)
        binding.spinnerChooseDepartment.adapter = spinnerAdapterDepartment
    }

    override fun initListener() {
        binding.btnAdd.setOnClickListener {
            val employee = Employee(address = binding.edtAddress.text.toString().trim(),
            username = binding.edtEmail.text.toString().trim(),
                password = "1",
                position = listPosition[binding.spinnerChoosePosition.selectedItem as Int].title,
                homeTown = binding.edtHometown.text.toString().trim(),
                name = binding.edtName.text.toString().trim(),
                tel = binding.edtTelephone.text.toString().toString(),
                gender = binding.root.findViewById<RadioButton>(binding.rgGender.checkedRadioButtonId).text.toString().trim(),
                dob = binding.edtDob.text.toString().trim(),
                email = binding.edtEmail.text.toString().trim()
            )
            employee.department = department
            viewModel.createEmployee(employee)
        }
        binding.edtDob.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, year, monthOfYear, dayOfMonth ->
                    val dat = (dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year)
                    binding.edtDob.setText(dat)
                },
                year,
                month,
                day
            )
            datePickerDialog.show()
        }
        binding.spinnerChooseDepartment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                department = listDepartment[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    override fun observerLiveData() {
        viewModel.apply {
            listDepartmentResults.observe(this@AddEmployeeFragment){ result ->
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
            employeeCreatedResult.observe(this@AddEmployeeFragment){ result ->
                when(result){
                    is Result.InProgress ->{
                    }
                    is Result.Success ->{
                        Toast.makeText(requireContext(),"Thêm thành công nhân viên mới", Toast.LENGTH_SHORT).show()
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