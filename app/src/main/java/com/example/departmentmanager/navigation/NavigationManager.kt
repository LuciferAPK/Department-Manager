package com.example.departmentmanager.navigation

import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentManager
import com.example.departmentmanager.R
import com.example.departmentmanager.ui.screen.login.LoginActivity
import com.example.departmentmanager.ui.screen.main.HomeFragment
import com.example.departmentmanager.ui.screen.main.MainActivity
import com.example.departmentmanager.ui.screen.main.profile.ProfileFragment
import com.example.departmentmanager.ui.screen.main.quanlinhansu.HumanResourceFragment
import com.example.departmentmanager.ui.screen.main.quanliphongban.DepartmentManagerFragment
import com.example.departmentmanager.ui.screen.main.quanlitask.TaskManagerFragment
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

    fun gotoProfileFragmentScreen(parentFragmentManager: FragmentManager) {
        val fragment = ProfileFragment()
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.content_frame, fragment)
        fragmentTransaction.addToBackStack(ProfileFragment::class.java.name)
        fragmentTransaction.commit()
    }

    fun gotoHumanResourceFragment(parentFragmentManager: FragmentManager){
        val fragment = HumanResourceFragment()
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_frame, fragment)
        fragmentTransaction.commit()
    }

    fun gotoDepartmentManagerFragment(parentFragmentManager: FragmentManager){
        val fragment = DepartmentManagerFragment()
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_frame, fragment)
        fragmentTransaction.commit()
    }

    fun gotoTaskManagerFragment(parentFragmentManager: FragmentManager){
        val fragment = TaskManagerFragment()
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_frame, fragment)
        fragmentTransaction.commit()
    }

}