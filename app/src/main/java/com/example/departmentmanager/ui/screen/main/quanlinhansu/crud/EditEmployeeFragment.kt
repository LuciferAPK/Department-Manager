package com.example.departmentmanager.ui.screen.main.quanlinhansu.crud

import android.app.DatePickerDialog
import android.view.View
import android.widget.AdapterView
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.departmentmanager.R
import com.example.departmentmanager.base.BaseFragment
import com.example.departmentmanager.base.Result
import com.example.departmentmanager.data.model.Department
import com.example.departmentmanager.data.model.Employee
import com.example.departmentmanager.data.model.ItemSpinner
import com.example.departmentmanager.databinding.LayoutEditEmployeeFragmentBinding
import com.example.departmentmanager.ui.adapter.CRUDSpinnerAdapter
import com.example.departmentmanager.ui.screen.main.quanlinhansu.HumanResourceViewModel
import java.util.*
import kotlin.collections.ArrayList

class EditEmployeeFragment : BaseFragment<LayoutEditEmployeeFragmentBinding>() {
    lateinit var spinnerAdapterDepartment : CRUDSpinnerAdapter
    lateinit var spinnerAdapterPosition : CRUDSpinnerAdapter
    lateinit var spinnerAdapterEmployee : CRUDSpinnerAdapter
    private var departmentCurrentSelected : Department ?= null
    private var employeeCurrentSelected : Employee ?= null
    private val listItemSpinnerDepartment: ArrayList<ItemSpinner> = arrayListOf()
    private val listItemSpinnerEmployee: ArrayList<ItemSpinner> = arrayListOf()
    private var listDepartment: ArrayList<Department> = arrayListOf()
    private var listEmployee: ArrayList<Employee> = arrayListOf()
    private val listPosition: ArrayList<ItemSpinner> = arrayListOf()
    private val viewModel : HumanResourceViewModel by activityViewModels()
    override fun getContentLayout(): Int {
        return R.layout.layout_edit_employee_fragment
    }

    override fun initView() {
        initAdapter()
    }

    fun initAdapter(){
        listPosition.apply {
            add(ItemSpinner(title = "LEADER"))
            add(ItemSpinner(title = "STAFF"))
            add(ItemSpinner(title = "CTO"))
            add(ItemSpinner(title = "MANAGER"))
            add(ItemSpinner(title = "INTERN"))
            add(ItemSpinner(title = "FRESHER"))
        }
        spinnerAdapterPosition = CRUDSpinnerAdapter(listPosition)
        binding.spinnerChoosePosition.adapter = spinnerAdapterPosition

        spinnerAdapterDepartment = CRUDSpinnerAdapter(listItemSpinnerDepartment)
        binding.spinnerChooseDepartment.adapter = spinnerAdapterDepartment

        spinnerAdapterEmployee = CRUDSpinnerAdapter(listItemSpinnerEmployee)
        binding.spinnerChooseEmployee.adapter = spinnerAdapterEmployee
    }

    override fun initListener() {
        binding.spinnerChooseDepartment.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                departmentCurrentSelected = listDepartment[position]
                viewModel.getEmployeeOfDepartment(departmentCurrentSelected?.id)
            }

        binding.spinnerChooseEmployee.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                employeeCurrentSelected = listEmployee[position]
                initData()
            }
        binding.btnReset.setOnClickListener {
            initData()
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

        binding.btnUpdate.setOnClickListener {
            employeeCurrentSelected?.address = binding.edtAddress.text.toString().trim()
            employeeCurrentSelected?.username = binding.edtEmail.text.toString().trim()
            employeeCurrentSelected?.position = listPosition[binding.spinnerChoosePosition.selectedItem as Int].title
            employeeCurrentSelected?.homeTown = binding.edtHometown.text.toString().trim()
            employeeCurrentSelected?.name = binding.edtName.text.toString().trim()
            employeeCurrentSelected?.tel = binding.edtTelephone.text.toString().trim()
            employeeCurrentSelected?.gender = binding.root.findViewById<RadioButton>(binding.rgGender.checkedRadioButtonId).text.toString().trim()
            employeeCurrentSelected?.dob = binding.edtDob.text.toString().trim()
            employeeCurrentSelected?.email = binding.edtEmail.text.toString().trim()
            employeeCurrentSelected?.let { it1 -> viewModel.createEmployee(it1) }
        }
    }

    fun initData(){
        binding.edtDob.setText(employeeCurrentSelected?.dob)
        binding.edtAddress.setText(employeeCurrentSelected?.address)
        binding.edtEmail.setText(employeeCurrentSelected?.email)
        binding.edtName.setText(employeeCurrentSelected?.name)
        binding.edtHometown.setText(employeeCurrentSelected?.homeTown)
        binding.edtTelephone.setText(employeeCurrentSelected?.tel)
        when(employeeCurrentSelected?.gender){
            "Male" -> {
                binding.rbMale.isChecked = true
            }
            "Female" -> {
                binding.rbFemale.isChecked = true
            }
            else -> binding.rbOther.isChecked = true
        }
        val positionSelected = listPosition.indexOfFirst { it.title == employeeCurrentSelected?.position }
        binding.spinnerChoosePosition.setSelection(positionSelected)
    }
    override fun observerLiveData() {
        viewModel.apply {
            listDepartmentResults.observe(this@EditEmployeeFragment){ result ->
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
            employeeByDepartmentResult.observe(this@EditEmployeeFragment){ result ->
                when(result){
                    is Result.InProgress ->{
                    }
                    is Result.Success ->{
                        listEmployee = result.data as ArrayList<Employee>
                        listEmployee.forEach{
                            listItemSpinnerEmployee.add(ItemSpinner(title = it.name))
                        }
                        spinnerAdapterEmployee.notifyDataSetChanged()
                    }
                    else ->{
                    }
                }
            }
            employeeCreatedResult.observe(this@EditEmployeeFragment){ result ->
                when(result){
                    is Result.InProgress ->{
                    }
                    is Result.Success ->{
                        Toast.makeText(requireContext(),"Sửa thành công nhân viên mới", Toast.LENGTH_SHORT).show()
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