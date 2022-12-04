package com.example.departmentmanager.ui.screen.login

import androidx.lifecycle.ViewModel
import com.example.departmentmanager.base.Result
import com.example.departmentmanager.base.SingleLiveEvent
import com.example.departmentmanager.data.model.Employee
import com.example.departmentmanager.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {
    val checkLoginEmployeeResult = SingleLiveEvent<Result<Employee>>()
    fun checkLogin(
        username: String?,
        password: String?
    ){
        val request = homeRepository.checkLogin(username, password)
        checkLoginEmployeeResult.addSource(request){
            checkLoginEmployeeResult.postValue(it)
        }
    }

    fun saveEmployee(e : Employee) = homeRepository.saveEmployee(e)
}