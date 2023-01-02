package com.example.departmentmanager.ui.screen.main

import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.departmentmanager.R
import com.example.departmentmanager.base.BaseActivity
import com.example.departmentmanager.databinding.ActivityMainBinding
import com.example.departmentmanager.navigation.NavigationManager
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(),
    NavigationView.OnNavigationItemSelectedListener {
    @Inject
    lateinit var navigationManager: NavigationManager

    private val onShowLeftMenu = object : OnShowLeftMenu{
        override fun show() {
            binding.leftMenu.openDrawer(GravityCompat.START)
        }

        override fun hide() {
            binding.leftMenu.closeDrawer(GravityCompat.START)
        }

    }

    override fun getContentLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        setUpToolbar()
        binding.navigationView.setNavigationItemSelectedListener(this)
        navigationManager.gotoHomeFragmentScreen(supportFragmentManager)
    }

    private fun setUpToolbar() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_menu)
        this.setSupportActionBar(binding.toolbar)
        Objects.requireNonNull(this.supportActionBar)
            ?.setDisplayHomeAsUpEnabled(true)
        this.supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onShowLeftMenu.show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initListener() {

    }

    override fun observerLiveData() {

    }

    override fun getLayoutLoading(): View? {
        return null
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_home ->{
                navigationManager.gotoHomeFragmentScreen(supportFragmentManager)
            }
        }
        onShowLeftMenu.hide()
        return true
    }

    override fun onBackPressed() {
    }

}