package com.christian.multinavexample.core.di

import com.christian.multinavexample.core.nav.MainCoordinatorImpl
import com.christian.multinavexample.core.nav.RootFlowCoordinatorImpl
import org.koin.dsl.module

val appModule = module {
    single { MainCoordinatorImpl() }
    single { RootFlowCoordinatorImpl() }
}