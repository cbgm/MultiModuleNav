package com.christian.multinavlib.navigation.coordinator

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.christian.multinavlib.navigation.deeplink.DeepLink
import com.christian.multinavlib.navigation.deeplink.DeepLinkHandler
import com.christian.multinavlib.navigation.extension.backStack
import com.christian.multinavlib.navigation.extension.backStackClean
import org.koin.core.KoinComponent
import org.koin.core.inject


abstract class BaseCoordinatorImpl : BaseCoordinator, KoinComponent {

   private val deepLinkHandler: DeepLinkHandler by inject()
   protected var isDeepLinkActive: Boolean = false

   protected var activity: FragmentActivity? = null
   protected lateinit var currentChildFragment: Fragment
   protected var currentFeatureFragment: Fragment? = null
   open var replaceableFragmentId: Int = 0

   /**
    * Method to start navigation from activity (initial navigation optional).
    * @param fragmentActivity The current app part activity
    */
   override fun start(fragmentActivity: FragmentActivity, withInitialNavigation: Boolean) {
      this.activity = fragmentActivity

      if (withInitialNavigation) {
         initialNavigation()
      }
   }

   /**
    * Method to start navigation from fragment (initial navigation optional).
    * @param fragment The current feature fragment
    */
   override fun start(fragment: Fragment, withInitialNavigation: Boolean) {
      this.currentFeatureFragment = fragment

      if (withInitialNavigation) {
         initialNavigation()
      }
   }

   /**
    * Method to start navigation from activity (data for deep linking optional) usually only called by the splash app part.
    * @param fragmentActivity The current app part activity
    */
   override fun start(fragmentActivity: FragmentActivity, uri: Uri?) {
      this.activity = fragmentActivity
      initialNavigation()
   }

   override fun initialNavigation() {
      if (this.deepLinkHandler.hasDeepLinks())
         navigateDeepLink()
      else
         navigateLink()
   }

   override fun back() {
      if (this.isDeepLinkActive) {
         onDeepLinkBack()
      } else {
         this.currentFeatureFragment?.backStack()
      }
   }

   override fun clear() {
      this.activity?.backStackClean()
   }

   /**
    * Method is called when back button is triggered and deep link is active.
    */
   abstract override fun onDeepLinkBack()

   /**
    * Method is called when initial navigation is triggered by start(..).
    * Should call the navigation to the first shown page inside a feature
    */
   abstract override fun navigateLink()

   /**
    * Method is called when initial navigation is triggered by start(..) and deep link was recognized
    * @param deepLink The found deep link
    */
   abstract override fun navigateDeepLink(deepLink: DeepLink)

   open fun navigateDeepLink(){
      deepLinkHandler.getDeepLink()
         ?.let {
            navigateDeepLink(it)
         }
   }

   /**
    * Method is called when navigation is triggered from CoordinatorManager.
    * @param routeKey The key for the route
    * @param navigationData The data needed to start up the route
    */
   abstract override fun route(
      routeKey: CoordinatorManager.State,
      navigationData: CoordinatorManager.NavigationData?
   ): Fragment?
}