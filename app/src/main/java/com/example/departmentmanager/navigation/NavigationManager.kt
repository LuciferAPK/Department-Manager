package com.example.departmentmanager.navigation

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentManager
import com.example.departmentmanager.R
import com.example.departmentmanager.ui.screen.login.LoginActivity
import com.example.departmentmanager.ui.screen.main.HomeFragment
import com.example.departmentmanager.ui.screen.main.MainActivity
import javax.inject.Singleton

@Singleton
class NavigationManager(private val context: Context) {
    fun gotoMainActivityScreen(){
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun gotoLoginActivityScreen(){
        val intent = Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun gotoHomeFragmentScreen(parentFragmentManager: FragmentManager) {
        val fragment = HomeFragment()
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_frame, fragment)
        fragmentTransaction.commit()
    }

}