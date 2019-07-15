package com.christian.multinavexample.core.nav

import com.christian.multinavlib.navigation.deeplink.DeepLinkIdentifier

enum class ExampleDeepLinkIdentifier: DeepLinkIdentifier {
    DETAIL {
        override fun hasParameter() = true
    },
    OVERVIEW2 {
        override fun hasParameter() = false
    },
}