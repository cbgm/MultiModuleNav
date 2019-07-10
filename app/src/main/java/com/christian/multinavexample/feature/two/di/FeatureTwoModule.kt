package com.christian.multinavexample.feature.two.di

import com.christian.multinavexample.feature.two.nav.FlowCoordinator
import org.koin.dsl.module

val featureTwoModule = module {
    single { FlowCoordinator() }
}