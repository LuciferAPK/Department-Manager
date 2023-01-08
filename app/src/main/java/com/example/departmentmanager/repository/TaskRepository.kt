package com.example.departmentmanager.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.departmentmanager.base.Result
import com.example.departmentmanager.data.model.Employee
import com.example.departmentmanager.data.model.Task
import com.example.departmentmanager.data.response.ErrorResponse
import com.example.departmentmanager.network.Api
import com.example.departmentmanager.network.END_POINT_TASK
import com.example.departmentmanager.preferences.PreferencesManager
import com.google.gson.Gson
import kotlinx.coroutines.*
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val api: Api,
    private val homeRepository: HomeRepository
) {
    fun createTask(task: Task? = null) : LiveData<Result<Task>> {
        val url = END_POINT_TASK
        val madeBy : Employee = homeRepository.getEmployee()
        task?.madeBy = madeBy
        val createdTaskLiveData = MutableLiveData<Result<Task>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            createdTaskLiveData.postValue(Result.Failure(400, throwable.message))
        }
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            createdTaskLiveData.postValue(Result.InProgress())
            val request = api.addTask(url = url, task = task)
            withContext(Dispatchers.Main) {
                if (request.isSuccessful) {
                    val taskResponse = Result.Success(request.body() as Task)
                    createdTaskLiveData.postValue(taskResponse)
                } else {
                    val strErr = request.errorBody()?.string()
                    val status = Gson().fromJson(strErr, ErrorResponse::class.java)
                    createdTaskLiveData.postValue(
                        Result.Failures(
                            status,
                            request.code(),
                            request.message()
                        )
                    )
                }
            }
        }
        return createdTaskLiveData
    }
}