package com.christian.multinavlib.navigation.coordinator

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.christian.multinavlib.navigation.deeplink.DeepLink


interface BaseCoordinator {

   fun start(fragmentActivity: FragmentActivity, withInitialNavigation: Boolean = true)

   fun start(fragment: Fragment, withInitialNavigation: Boolean = true)

   fun start(fragmentActivity: FragmentActivity, uri: Uri?)

   fun initialNavigation()

   fun back()

   fun clear()

   fun onDeepLinkBack()

   fun navigateLink()

   fun navigateDeepLink(deepLink: DeepLink)

   fun route(routeKey: CoordinatorManager.State, navigationData: CoordinatorManager.NavigationData?): Fragment?
}