package com.example.departmentmanager.ui.screen.main.quanlinhansu

import androidx.lifecycle.ViewModel
import com.example.departmentmanager.base.SingleLiveEvent
import com.example.departmentmanager.data.model.Department
import com.example.departmentmanager.base.Result
import com.example.departmentmanager.data.model.Employee
import com.example.departmentmanager.repository.HumanResourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HumanResourceViewModel @Inject constructor(
    private val humanResourceRepository: HumanResourceRepository
): ViewModel() {
    val listDepartmentResults = SingleLiveEvent<Result<List<Department>>>()
    fun getAllDepartment(){
        val request = humanResourceRepository.getAllDepartment()
        listDepartmentResults.addSource(request){
            listDepartmentResults.postValue(it)
        }
    }

    val employeeCreatedResult = SingleLiveEvent<Result<Employee>>()
    fun createEmployee(
        body: Employee
    ){
        val request = humanResourceRepository.createEmployee(body)
        employeeCreatedResult.addSource(request){
            employeeCreatedResult.postValue(it)
        }
    }

    val employeeByDepartmentResult = SingleLiveEvent<Result<List<Employee>>>()
    fun getEmployeeOfDepartment(
        idDepartment: Int ?= 0
    ){
        val request = humanResourceRepository.getEmployeeOfDepartment(idDepartment)
        employeeByDepartmentResult.addSource(request){
            employeeByDepartmentResult.postValue(it)
        }
    }

    val employeeUpdateResult = SingleLiveEvent<Result<Employee>>()
    fun updateEmployee(
        employee: Employee
    ){
        val request = humanResourceRepository.createEmployee(employee)
        employeeUpdateResult.addSource(request){
            employeeUpdateResult.postValue(it)
        }
    }
}