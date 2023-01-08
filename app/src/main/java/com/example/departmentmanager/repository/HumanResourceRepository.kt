package com.example.departmentmanager.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.departmentmanager.base.Result
import com.example.departmentmanager.data.model.Department
import com.example.departmentmanager.data.model.Employee
import com.example.departmentmanager.data.response.ErrorResponse
import com.example.departmentmanager.network.Api
import com.example.departmentmanager.network.END_POINT_ADD_EMPLOYEE
import com.example.departmentmanager.network.END_POINT_DEPARTMENT
import com.example.departmentmanager.network.END_POINT_GET_EMPLOYEE_BY_DEPARTMENT
import com.example.departmentmanager.preferences.PreferencesManager
import com.google.gson.Gson
import kotlinx.coroutines.*
import javax.inject.Inject

class HumanResourceRepository @Inject constructor(
    private val api: Api, private val preferencesManager: PreferencesManager,
    private val homeRepository: HomeRepository
) {
    fun getAllDepartment(): LiveData<Result<List<Department>>> {
        val url = END_POINT_DEPARTMENT
        val departmentsLiveData = MutableLiveData<Result<List<Department>>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            departmentsLiveData.postValue(Result.Failure(400, throwable.message))
        }
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            departmentsLiveData.postValue(Result.InProgress())
            val request = api.getAllDepartment(url = url)
            withContext(Dispatchers.Main) {
                if (request.isSuccessful) {
                    val listDepartmentResponse = Result.Success(request.body() as List<Department>)
                    departmentsLiveData.postValue(listDepartmentResponse)
                } else {
                    val strErr = request.errorBody()?.string()
                    val status = Gson().fromJson(strErr, ErrorResponse::class.java)
                    departmentsLiveData.postValue(
                        Result.Failures(
                            status,
                            request.code(),
                            request.message()
                        )
                    )
                }
            }
        }
        return departmentsLiveData
    }

    fun createEmployee(employee: Employee): LiveData<Result<Employee>> {
        val url = END_POINT_ADD_EMPLOYEE
        val createdEmployeeLiveData = MutableLiveData<Result<Employee>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            createdEmployeeLiveData.postValue(Result.Failure(400, throwable.message))
        }
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            createdEmployeeLiveData.postValue(Result.InProgress())
            val request = api.addEmployee(url = url, employee = employee)
            withContext(Dispatchers.Main) {
                if (request.isSuccessful) {
                    val employeeResponse = Result.Success(request.body() as Employee)
                    createdEmployeeLiveData.postValue(employeeResponse)
                } else {
                    val strErr = request.errorBody()?.string()
                    val status = Gson().fromJson(strErr, ErrorResponse::class.java)
                    createdEmployeeLiveData.postValue(
                        Result.Failures(
                            status,
                            request.code(),
                            request.message()
                        )
                    )
                }
            }
        }
        return createdEmployeeLiveData
    }

    fun getEmployeeOfDepartment(idDepartment: Int?): LiveData<Result<List<Employee>>> {
        val url = END_POINT_GET_EMPLOYEE_BY_DEPARTMENT
        var idDepartmentIfNeed :Int ?= 0
        if(idDepartment == null){
            val employee = homeRepository.getEmployee()
            idDepartmentIfNeed = employee.id
        }
        val employeesOfDepartment = MutableLiveData<Result<List<Employee>>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            employeesOfDepartment.postValue(Result.Failure(400, throwable.message))
        }
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            employeesOfDepartment.postValue(Result.InProgress())
            val request = api.getEmployeeOfDepartment(url = url, idDepartment = idDepartment ?: idDepartmentIfNeed)
            withContext(Dispatchers.Main) {
                if (request.isSuccessful) {
                    val employeeResponse = Result.Success(request.body() as List<Employee>)
                    employeesOfDepartment.postValue(employeeResponse)
                } else {
                    val strErr = request.errorBody()?.string()
                    val status = Gson().fromJson(strErr, ErrorResponse::class.java)
                    employeesOfDepartment.postValue(
                        Result.Failures(
                            status,
                            request.code(),
                            request.message()
                        )
                    )
                }
            }
        }
        return employeesOfDepartment
    }

    fun updateEmployee(employee: Employee): LiveData<Result<Employee>> {
        val url = END_POINT_ADD_EMPLOYEE
        val updatedEmployeeLiveData = MutableLiveData<Result<Employee>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            updatedEmployeeLiveData.postValue(Result.Failure(400, throwable.message))
        }
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            updatedEmployeeLiveData.postValue(Result.InProgress())
            val request = api.addEmployee(url = url, employee = employee)
            withContext(Dispatchers.Main) {
                if (request.isSuccessful) {
                    val employeeResponse = Result.Success(request.body() as Employee)
                    updatedEmployeeLiveData.postValue(employeeResponse)
                } else {
                    val strErr = request.errorBody()?.string()
                    val status = Gson().fromJson(strErr, ErrorResponse::class.java)
                    updatedEmployeeLiveData.postValue(
                        Result.Failures(
                            status,
                            request.code(),
                            request.message()
                        )
                    )
                }
            }
        }
        return updatedEmployeeLiveData
    }
}