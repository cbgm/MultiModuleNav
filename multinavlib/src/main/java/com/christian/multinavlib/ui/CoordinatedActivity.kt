package com.christian.multinavlib.ui

import androidx.fragment.app.FragmentActivity
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import org.koin.android.ext.android.inject

abstract class CoordinatedActivity: FragmentActivity() {
    protected val coordinatorManager: CoordinatorManager by inject()
}