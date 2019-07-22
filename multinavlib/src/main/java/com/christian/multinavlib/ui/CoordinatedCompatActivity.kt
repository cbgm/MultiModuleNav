package com.christian.multinavlib.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import org.koin.android.ext.android.inject


abstract class CoordinatedCompatActivity: AppCompatActivity() {
    protected val coordinatorManager: CoordinatorManager by inject()

    override fun onNewIntent(intent: Intent?) {
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
}