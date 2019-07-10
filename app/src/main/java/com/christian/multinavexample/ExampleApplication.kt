package com.christian.multinavexample

import android.app.Application
import com.christian.multinavexample.di.appModule
import com.christian.multinavexample.feature.one.di.featureOneModule
import com.christian.multinavexample.feature.one.nav.OneFlowCoordinator
import com.christian.multinavexample.feature.two.di.featureTwoModule
import com.christian.multinavexample.feature.two.nav.TwoFlowCoordinator
import com.christian.multinavlib.di.navModule
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


@Suppress("unused")
class ExampleApplication : Application() {
    private val coordinatorManager: CoordinatorManager by inject()
    private val mainCoordinatorImpl: MainCoordinatorImpl by inject()
    private val twoFlowCoordinator: TwoFlowCoordinator by inject()
    private val rootFlowCoordinatorImpl: RootFlowCoordinatorImpl by inject()
    private val oneFlowCoordinator: OneFlowCoordinator by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ExampleApplication)
            modules(listOf(appModule, featureOneModule, featureTwoModule, navModule))
        }
        initCoordinator()
    }

    private fun initCoordinator() {
        coordinatorManager.run {
            registerApplicationPartCoordinator(rootFlowCoordinatorImpl)
            registerMainCoordinator(mainCoordinatorImpl)
            registerFeatureCoordinator(
                MainCoordinatorImpl.States.FEATURE_ONE,
                oneFlowCoordinator
            )
            registerFeatureCoordinator(
                MainCoordinatorImpl.States.FEATURE_TWO,
                twoFlowCoordinator
            )
        }
    }
}