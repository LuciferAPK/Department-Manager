package com.example.departmentmanager.ui.screen.main

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import com.example.departmentmanager.R
import com.example.departmentmanager.application.ApplicationContext
import com.example.departmentmanager.base.BaseActivity
import com.example.departmentmanager.databinding.ActivityMainBinding
import com.example.departmentmanager.databinding.LayoutHeaderNavBinding
import com.example.departmentmanager.navigation.NavigationManager
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.layout_header_nav.view.*
import java.io.ByteArrayOutputStream
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(),
    NavigationView.OnNavigationItemSelectedListener {
    @Inject
    lateinit var navigationManager: NavigationManager
    private var selectedPhotoUri: Uri? = null
    private val headerNavBinding by lazy {
        LayoutHeaderNavBinding.bind(binding.navigationView.getHeaderView(0))
    }

    private val imagePickerFragment by lazy {
        ImagePickerFragment(
            onCameraClicked = { clickCamera() },
            onLibraryClicked = { clickLibrary() })
    }

    private val onShowLeftMenu = object : OnShowLeftMenu{
        override fun show() {
            binding.leftMenu.openDrawer(GravityCompat.START)
        }

        override fun hide() {
            binding.leftMenu.closeDrawer(GravityCompat.START)
        }

    }

    private fun clickCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, Camera_Code)
        imagePickerFragment.dismiss()
    }

    private fun clickLibrary() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, Library_Code)
        imagePickerFragment.dismiss()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onShowLeftMenu.show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun initListener() {
        headerNavBinding.tvName.text = ApplicationContext.sessionContext.name
        headerNavBinding.tvEmail.text = ApplicationContext.sessionContext.email
        headerNavBinding.camera.setOnClickListener {
            selectedCamera()
        }
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
            R.id.nav_profile ->{
                navigationManager.gotoProfileFragmentScreen(supportFragmentManager)
            }
            R.id.nav_logout -> {
                navigationManager.gotoLoginActivityScreen()
            }
        }
        onShowLeftMenu.hide()
        return true
    }

    override fun onBackPressed() {
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Camera_Code) {
            val pic = data?.getParcelableExtra<Bitmap>("data")
            if (pic != null) {
                headerNavBinding.imgAvt.setImageURI(getImageUriFromBitmap(this, pic))
            }
        }

        if (requestCode == Library_Code) {
            selectedPhotoUri = data?.data
            if (selectedPhotoUri != null) {
                headerNavBinding.imgAvt.setImageURI(selectedPhotoUri)
            }
        }
    }

    //convert Bitmap to Uri
    private fun getImageUriFromBitmap(context: Context, bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path =
            MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "Title", null)
        return Uri.parse(path.toString())
    }

    private fun selectedCamera() {
        headerNavBinding.camera.setOnClickListener {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), 2507
            )
            imagePickerFragment.show(supportFragmentManager, null)
        }
    }

    companion object {
        const val Camera_Code = 123
        const val Library_Code = 456
    }
}