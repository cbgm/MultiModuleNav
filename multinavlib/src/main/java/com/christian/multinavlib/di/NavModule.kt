package com.christian.multinavlib.di

import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.christian.multinavlib.navigation.deeplink.DeepLinkHandler
import org.koin.dsl.module

val navModule = module {
    single { DeepLinkHandler() }
    single { CoordinatorManager() }
}