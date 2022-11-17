package com.example.departmentmanager.network

import com.example.departmentmanager.data.model.Category
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {
    @GET
    suspend fun getItemByCategory(
        @Url url: String?,

    ) : Response<Any>

    @GET
    suspend fun  getAllCategory(
        @Url url: String?
    ): Response<List<Category>>

}