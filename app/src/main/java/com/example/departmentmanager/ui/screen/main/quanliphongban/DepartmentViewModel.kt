package com.example.departmentmanager.ui.screen.main.quanliphongban

import androidx.lifecycle.ViewModel
import com.example.departmentmanager.base.Result
import com.example.departmentmanager.base.SingleLiveEvent
import com.example.departmentmanager.data.model.Department
import com.example.departmentmanager.repository.DepartmentRepository
import com.example.departmentmanager.repository.HumanResourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DepartmentViewModel @Inject constructor(
    private val departmentRepository: DepartmentRepository,
    private val humanResourceRepository: HumanResourceRepository
) : ViewModel() {
    val departmentCreatedResult = SingleLiveEvent<Result<Department>>()
    fun createDepartment(department: Department) {
        val request = departmentRepository.createDepartment(department)
        departmentCreatedResult.addSource(request) {
            departmentCreatedResult.postValue(it)
        }
    }

    val listDepartmentResults = SingleLiveEvent<Result<List<Department>>>()
    fun getAllDepartment() {
        val request = humanResourceRepository.getAllDepartment()
        listDepartmentResults.addSource(request) {
            listDepartmentResults.postValue(it)
        }
    }
}