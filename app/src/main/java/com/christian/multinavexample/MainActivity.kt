package com.christian.multinavexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val coordinatorManager = ExampleCoordinatorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("gg", "start")
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            coordinatorManager.navigateToFeature(ExampleCoordinatorManager.States.FEATURE_ONE)
        }
        button2.setOnClickListener {
            coordinatorManager.navigateToFeature(ExampleCoordinatorManager.States.FEATURE_TWO)
        }
        coordinatorManager.startNavigation(this)
    }

    override fun onBackPressed() {
        coordinatorManager.backStackFeature()
    }
}
