package com.example.departmentmanager.data.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Employee(
    @SerializedName("address")
    var address: String? = "",
    @SerializedName("department")
    var department: Department? = null,
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("dob")
    var dob: Long? = 0,
    @SerializedName("email")
    var email: String? = "",
    @SerializedName("gender")
    var gender: String? = "",
    @SerializedName("homeTown")
    var homeTown: String? = "",
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("password")
    var password: String? = "",
    @SerializedName("position")
    var position: String? = "",
    @SerializedName("tel")
    var tel: String? = "",
    @SerializedName("username")
    var username: String? = ""
): Serializable