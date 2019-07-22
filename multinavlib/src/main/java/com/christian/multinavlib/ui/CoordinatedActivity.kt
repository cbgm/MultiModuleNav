package com.christian.multinavlib.ui

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import org.koin.android.ext.android.inject


abstract class CoordinatedActivity: FragmentActivity() {
    protected val coordinatorManager: CoordinatorManager by inject()

    override fun onNewIntent(intent: Intent?) {
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }
}