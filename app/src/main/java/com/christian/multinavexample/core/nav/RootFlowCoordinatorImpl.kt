package com.christian.multinavexample.core.nav

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.christian.annotation.RootCoordinator
import com.christian.multinavexample.MainActivity
import com.christian.multinavexample.SplashActivity
import com.christian.multinavlib.navigation.coordinator.BaseCoordinatorImpl
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.christian.multinavlib.navigation.deeplink.DeepLink

@RootCoordinator
class RootFlowCoordinatorImpl : BaseCoordinatorImpl() {

   enum class States: CoordinatorManager.State {
      SPLASH,
      MAIN
   }

   override fun navigateDeepLink(deepLink: DeepLink) {
      showMain()
   }

   override fun navigateLink() {
      showMain()
   }

   private fun showMain(){
      val intentToStart = Intent(activity, MainActivity::class.java)
      intentToStart.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
      activity?.startActivity(intentToStart)
      activity?.finish()
   }

   override fun start(fragmentActivity: FragmentActivity, withInitialNavigation: Boolean) {
      super.start(fragmentActivity, false)
      showMain()
   }

   private fun showSplash() {
      val intentToStart = Intent(activity, SplashActivity::class.java)
      intentToStart.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
      activity?.startActivity(intentToStart)
      activity?.finish()
   }

   override fun onDeepLinkBack() {
      //not needed
   }

   override fun route(routeKey: CoordinatorManager.State, navigationData: CoordinatorManager.NavigationData?): Fragment? {
      when(routeKey) {
         States.SPLASH -> showSplash()
         States.MAIN -> showMain()
      }
      return null
   }
}
