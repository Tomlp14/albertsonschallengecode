package com.tommyappdevs.albertsonsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.tommyappdevs.albertsonsapp.model.repository.UIState
import com.tommyappdevs.albertsonsapp.view.DisplayFragment
import com.tommyappdevs.albertsonsapp.viewmodel.AcronymViewModel

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createDisplayFragment()
    }

    private fun createDisplayFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.container, DisplayFragment()).commit()
    }

}