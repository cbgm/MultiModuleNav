package com.christian.multinavexample.core.nav

import com.christian.annotation.BindFeatureStates
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager

@BindFeatureStates
enum class FeatureStates(val stateAsInt: Int): CoordinatorManager.State {
    FEATURE_ONE(1),
    FEATURE_TWO(1)
}