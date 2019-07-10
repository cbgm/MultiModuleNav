package com.christian.multinavlib.ui

import androidx.fragment.app.Fragment
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import org.koin.android.ext.android.inject

abstract class SubFeatureFragment(): Fragment() {
    protected val coordinatorManager: CoordinatorManager by inject()
}