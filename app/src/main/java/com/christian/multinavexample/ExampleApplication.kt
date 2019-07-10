package com.christian.multinavexample

import android.app.Application
import com.christian.multinavexample.feature.two.nav.FlowCoordinator


@Suppress("unused")
class ExampleApplication: Application(){
   private val coordinatorManager = ExampleCoordinatorManager
   private val mainCoordinatorImpl = MainCoordinatorImpl
   private val twoCoordinator = FlowCoordinator
   private val rootFlowCoordinatorImpl = RootFlowCoordinatorImpl
   private val oneCoordinator = com.christian.multinavexample.feature.one.nav.FlowCoordinator

   override fun onCreate() {
       super.onCreate()

      initCoordinator()
   }

   private fun initCoordinator(){
      coordinatorManager.run {
         registerApplicationPartCoordinator(rootFlowCoordinatorImpl)
         registerMainCoordinator(mainCoordinatorImpl)
         registerFeatureCoordinator(
               ExampleCoordinatorManager.States.FEATURE_ONE,
               oneCoordinator
         )
         registerFeatureCoordinator(
               ExampleCoordinatorManager.States.FEATURE_TWO,
               twoCoordinator
         )
      }
   }
}