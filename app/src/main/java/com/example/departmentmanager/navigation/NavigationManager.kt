package com.example.departmentmanager.navigation

import android.content.Context
import android.content.Intent
import com.example.departmentmanager.ui.screen.main.MainActivity
import javax.inject.Singleton

@Singleton
class NavigationManager(private val context: Context) {
    fun gotoMainActivityScreen(){
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}