package com.example.departmentmanager.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.departmentmanager.base.Result
import com.example.departmentmanager.data.model.Department
import com.example.departmentmanager.data.response.ErrorResponse
import com.example.departmentmanager.network.Api
import com.example.departmentmanager.network.END_POINT_DEPARTMENT
import com.google.gson.Gson
import kotlinx.coroutines.*
import javax.inject.Inject

class DepartmentRepository @Inject constructor(private val api: Api) {

    fun createDepartment(department: Department) : LiveData<Result<Department>> {
        val url = END_POINT_DEPARTMENT
        val createdDepartmentLiveData = MutableLiveData<Result<Department>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            createdDepartmentLiveData.postValue(Result.Failure(400, throwable.message))
        }
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            createdDepartmentLiveData.postValue(Result.InProgress())
            val request = api.addDepartment(url = url, department = department)
            withContext(Dispatchers.Main) {
                if (request.isSuccessful) {
                    val departmentResponse = Result.Success(request.body() as Department)
                    createdDepartmentLiveData.postValue(departmentResponse)
                }
                else {
                    val strErr = request.errorBody()?.string()
                    val status = Gson().fromJson(strErr, ErrorResponse::class.java)
                    createdDepartmentLiveData.postValue(Result.Failures(status, request.code(), request.message()))
                }
            }
        }
        return createdDepartmentLiveData
    }
}