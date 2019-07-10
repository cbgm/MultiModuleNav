package com.christian.multinavexample.feature.one.di

import com.christian.multinavexample.feature.one.nav.OneFlowCoordinator
import org.koin.dsl.module

val featureOneModule = module {
    single { OneFlowCoordinator() }
}