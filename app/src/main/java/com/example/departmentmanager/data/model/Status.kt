package com.example.departmentmanager.data.model

import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("message")
    val message: String,
    @SerializedName("statusCode")
    val statusCode: Int
)