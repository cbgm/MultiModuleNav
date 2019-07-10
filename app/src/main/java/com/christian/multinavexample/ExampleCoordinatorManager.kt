package com.christian.multinavexample

import com.christian.multinavlib.navigation.coordinator.CoordinatorManager

object ExampleCoordinatorManager: CoordinatorManager() {
    enum class States: State {
        FEATURE_ONE,
        FEATURE_TWO
    }

}