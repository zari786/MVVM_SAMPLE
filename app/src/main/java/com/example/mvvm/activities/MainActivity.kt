package com.example.mvvm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.mvvm.R
import com.example.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(activityMainBinding.root)

        setSupportActionBar(activityMainBinding.mainToolbar)

        navController = Navigation.findNavController(this, R.id.navHostFragment)

        NavigationUI.setupWithNavController(activityMainBinding.navigationView,navController)
        NavigationUI.setupActionBarWithNavController(this, navController, activityMainBinding.drawerLayout)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,activityMainBinding.drawerLayout)
    }
}