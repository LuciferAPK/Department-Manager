package com.example.departmentmanager.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewpagerAdapter (
    fragmentManager: FragmentManager,
    lifeCycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifeCycle) {

    private val fragmentList: MutableList<Fragment> = ArrayList()
    private val titles: MutableList<String> = ArrayList()

    fun getTitles(): MutableList<String> {
        return titles
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        titles.add(title)
    }

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun clearAllData(){
        fragmentList.clear()
        titles.clear()
    }

    fun getFragmentByPosition(position: Int) = fragmentList[position]
}


