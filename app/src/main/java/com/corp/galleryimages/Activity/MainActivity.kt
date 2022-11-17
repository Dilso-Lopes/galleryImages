package com.corp.galleryimages.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.corp.galleryimages.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    private val TAG = this::class.java.simpleName
    private lateinit var navController: NavController
    private lateinit var barConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val fragmetGallery = supportFragmentManager.findFragmentById(R.id.gallery_fragment) as NavHostFragment
        navController = fragmetGallery.navController
        barConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, barConfiguration)

    }

}

