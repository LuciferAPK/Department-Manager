package com.example.departmentmanager.ui.screen.main.quanlitask

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.departmentmanager.base.Result
import com.example.departmentmanager.base.SingleLiveEvent
import com.example.departmentmanager.data.model.Employee
import com.example.departmentmanager.data.model.Task
import com.example.departmentmanager.network.Api
import com.example.departmentmanager.repository.HumanResourceRepository
import com.example.departmentmanager.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskManagerViewModel @Inject constructor(
    private val api: Api,
    private val humanResourceRepository: HumanResourceRepository,
    private val taskRepository: TaskRepository
): ViewModel() {

    val employeeByDepartmentResult = SingleLiveEvent<Result<List<Employee>>>()
    fun getEmployeeOfDepartment(
        idDepartment: Int? = null
    ) {
        val request = humanResourceRepository.getEmployeeOfDepartment(idDepartment)
        employeeByDepartmentResult.addSource(request) {
            employeeByDepartmentResult.postValue(it)
        }
    }

    val createdTaskResult = SingleLiveEvent<Result<Task>>()
    fun createTask(
        task: Task ?= null
    ) {
        val request = taskRepository.createTask(task)
        createdTaskResult.addSource(request) {
            createdTaskResult.postValue(it)
        }
    }
}