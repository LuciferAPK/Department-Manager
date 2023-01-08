package com.example.departmentmanager.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtils {
    companion object{
        @SuppressLint("SimpleDateFormat")
        fun  millisecondsToDate(milliseconds: Long): String{
            val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")

            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = milliseconds
            return formatter.format(calendar.time)
        }
    }
}