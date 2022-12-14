package com.example.departmentmanager.data.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Department(
    @SerializedName("capacity")
    var capacity: Int? = null,
    @SerializedName("createdtime")
    var createdTime: Long? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("function")
    var function: String? = null
) : Serializable