package com.example.departmentmanager.network

import com.example.departmentmanager.data.model.Department
import com.example.departmentmanager.data.model.Employee
import com.example.departmentmanager.data.model.Task
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {
    @GET
    suspend fun checkLogin(
        @Url url: String?,
        @Query("username") username: String?,
        @Query("password") password: String?
        ) : Response<Employee?>

    @POST
    suspend fun addEmployee(
        @Url url: String?,
        @Body employee: Employee?
    ) : Response<Employee?>

    @POST
    suspend fun addTask(
        @Url url: String?,
        @Body task: Task?
    ) : Response<Task?>

    @POST
    suspend fun addDepartment(
        @Url url: String?,
        @Body department: Department?
    ) : Response<Department?>

    @GET
    suspend fun getAllDepartment(
        @Url url: String?
    ) : Response<List<Department?>>

    @GET
    suspend fun getEmployeeOfDepartment(
        @Url url: String?,
        @Query("idDepartment") idDepartment: Int?
    ) : Response<List<Employee?>>
}