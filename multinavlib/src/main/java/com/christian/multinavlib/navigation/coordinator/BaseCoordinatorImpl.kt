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

   override fun start(fragmentActivity: FragmentActivity, withInitialNavigation: Boolean) {
      this.activity = fragmentActivity

      if (withInitialNavigation) {
         initialNavigation()
      }
   }

   override fun start(fragment: Fragment, withInitialNavigation: Boolean) {
      this.currentFeatureFragment = fragment

      if (withInitialNavigation) {
         initialNavigation()
      }
   }

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

   abstract override fun onDeepLinkBack()

   abstract override fun navigateLink()

   abstract override fun navigateDeepLink(deepLink: DeepLink)

   override fun navigateDeepLink(){
      deepLinkHandler.getDeepLink()
         ?.let {
            navigateDeepLink(it)
         }
   }

   abstract override fun route(
         routeKey: CoordinatorManager.State,
         navigationData: CoordinatorManager.NavigationData?
   ): Fragment?
}