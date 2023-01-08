package com.example.departmentmanager.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Task(
    @SerializedName("estimate")
    var estimate: String? = null,
    @SerializedName("createdtime")
    var createdTime: Long? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("details")
    var details: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("madeby")
    var madeBy: Employee? = null,
    @SerializedName("assignto")
    var assignTo: Employee? = null
) : Serializable