package com.christian.multinavexample

import com.christian.multinavlib.navigation.deeplink.DeepLinkIdentifier

enum class ExampleDeeplinkIdentifier: DeepLinkIdentifier {
    DETAIL {
        override fun hasParameter() = true
    },
    OVERVIEW2 {
        override fun hasParameter() = false
    },
}