package com.christian.multinavexample.feature.two.di

import com.christian.multinavexample.feature.two.nav.TwoFlowCoordinator
import org.koin.dsl.module

val featureTwoModule = module {
    single { TwoFlowCoordinator() }
}