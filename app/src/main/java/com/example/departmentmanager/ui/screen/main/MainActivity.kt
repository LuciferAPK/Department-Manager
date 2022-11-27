package com.example.departmentmanager.ui.screen.main

import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.departmentmanager.R
import com.example.departmentmanager.base.BaseActivity
import com.example.departmentmanager.databinding.ActivityMainBinding
import com.example.departmentmanager.navigation.NavigationManager
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(), NavigationView.OnNavigationItemSelectedListener {
    @Inject
    lateinit var navigationManager: NavigationManager
    private val actionBarDrawerToggle by lazy {
        ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.nav_drawer_open,
            R.string.nav_drawer_close
        )
    }

    override fun getContentLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        navigationManager.gotoHomeFragmentScreen(supportFragmentManager)
        binding.navigationView.setNavigationItemSelectedListener(this)
        binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
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
            R.id.nav_home -> {
                if (mCurrentFragment != FRAGMENT_HOME) {
                    navigationManager.gotoHomeFragmentScreen(supportFragmentManager)
                    mCurrentFragment = FRAGMENT_HOME
                }
            }
            R.id.nav_user -> {
                if (mCurrentFragment != FRAGMENT_INFO) {
                    navigationManager.gotoInfoFragmentScreen(supportFragmentManager)
                    mCurrentFragment = FRAGMENT_INFO
                }
            }
            R.id.nav_mk -> {
                if (mCurrentFragment != FRAGMENT_PASS) {
                    navigationManager.gotoChangePassFragmentScreen(supportFragmentManager)
                    mCurrentFragment = FRAGMENT_PASS
                }
            }
            R.id.nav_lo -> {
                navigationManager.gotoLoginActivityScreen()
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else super.onBackPressed()
    }

    companion object {
        private const val FRAGMENT_HOME = 0
        private const val FRAGMENT_INFO = 1
        private const val FRAGMENT_PASS = 2

        private var mCurrentFragment = FRAGMENT_HOME
    }
}