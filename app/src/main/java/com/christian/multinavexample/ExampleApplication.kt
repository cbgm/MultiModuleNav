package com.christian.multinavexample

import android.app.Application
import com.christian.multinavexample.core.di.appModule
import com.christian.multinavexample.feature.one.di.featureOneModule
import com.christian.multinavexample.feature.two.di.featureTwoModule
import com.christian.multinavlib.MultiNav
import com.christian.multinavlib.di.navModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


@Suppress("unused")
class ExampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ExampleApplication)
            modules(listOf(appModule, featureOneModule, featureTwoModule, navModule))
        }
        MultiNav().init()
    }
}