package com.christian.multinavexample

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : FragmentActivity() {

    private val coordinatorManager: CoordinatorManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("gg", "start")
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            coordinatorManager.navigateToFeature(MainCoordinatorImpl.States.FEATURE_ONE)
        }
        button2.setOnClickListener {
            coordinatorManager.navigateToFeature(MainCoordinatorImpl.States.FEATURE_TWO)
        }
        coordinatorManager.startNavigation(this)
    }

    override fun onBackPressed() {
        coordinatorManager.backStackFeature()
    }
}
