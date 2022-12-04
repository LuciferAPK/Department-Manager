package com.example.departmentmanager.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.departmentmanager.data.model.Employee
import com.example.departmentmanager.network.Api
import com.example.departmentmanager.preferences.PreferencesManager
import com.example.householdappliances.network.END_POINT_LOGIN
import com.google.gson.Gson
import kotlinx.coroutines.*
import com.example.departmentmanager.base.Result
import com.example.departmentmanager.data.response.ErrorResponse
import com.example.departmentmanager.preferences.EMPLOYEE
import com.example.departmentmanager.utils.GsonUtils
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val api: Api,
    private val preferencesManager: PreferencesManager
) {
    fun checkLogin(
        username: String?,
        password: String?
    ): LiveData<Result<Employee>> {
        val url: String? = END_POINT_LOGIN
        val employeeLiveData = MutableLiveData<Result<Employee>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            employeeLiveData.postValue(Result.Failure(400, throwable.message))
        }
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            employeeLiveData.postValue(Result.InProgress())
            val request = api.checkLogin(url = url,username = username, password = password)
            withContext(Dispatchers.Main) {
                if (request.isSuccessful) {
                    val employeeResponse = Result.Success(request.body() as Employee)
                    employeeLiveData.postValue(employeeResponse)
                }
                else {
                    val strErr = request.errorBody()?.string()
                    val status = Gson().fromJson(strErr, ErrorResponse::class.java)
                    employeeLiveData.postValue(Result.Failures(status, request.code(), request.message()))
                }
            }
        }
        return employeeLiveData
    }

    fun saveEmployee(e: Employee) = preferencesManager.save(EMPLOYEE,GsonUtils.serialize(e,Employee::class.java))
}