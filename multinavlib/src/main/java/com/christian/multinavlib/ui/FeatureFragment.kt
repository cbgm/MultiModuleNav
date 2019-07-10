package com.christian.multinavlib.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import org.koin.android.ext.android.inject

abstract class FeatureFragment(private val featureState: CoordinatorManager.State): Fragment() {
    protected val coordinatorManager: CoordinatorManager by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coordinatorManager.switchFeatureCoordinator(featureState, this)
    }
}