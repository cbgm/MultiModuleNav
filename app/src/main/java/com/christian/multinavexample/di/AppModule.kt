package com.christian.multinavexample.di

import com.christian.multinavexample.MainCoordinatorImpl
import com.christian.multinavexample.RootFlowCoordinatorImpl
import org.koin.dsl.module

val appModule = module {
    single { MainCoordinatorImpl() }
    single { RootFlowCoordinatorImpl() }
}