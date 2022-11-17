package com.example.departmentmanager.data.response

import com.example.departmentmanager.data.model.Status
import com.google.gson.annotations.SerializedName

class ErrorResponse {
    @SerializedName("status")
    val status: Status? = null
}