package com.christian.multinavexample.core.nav

import androidx.fragment.app.Fragment
import com.christian.annotation.BindFeatureStates
import com.christian.annotation.MainCoordinator
import com.christian.multinavexample.R
import com.christian.multinavexample.feature.one.FeatureFragOne
import com.christian.multinavexample.feature.two.FeatureFragTwo
import com.christian.multinavlib.navigation.coordinator.BaseCoordinatorImpl
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.christian.multinavlib.navigation.deeplink.DeepLink
import com.christian.multinavlib.navigation.extension.replaceFragmentNoBackStack

@MainCoordinator
class MainCoordinatorImpl : BaseCoordinatorImpl() {

    override var replaceableFragmentId: Int = R.id.feature_container



    override fun navigateDeepLink(deepLink: DeepLink) {
        when (deepLink.action) {
            ExampleDeepLinkIdentifier.OVERVIEW2 -> showFeatureTwo()
            else -> showFeatureOne()
        }
    }

    private fun showFeatureOne(): Fragment {
        val frag = FeatureFragOne.newInstance()
        this.activity?.replaceFragmentNoBackStack(frag, replaceableFragmentId, "ONE")
        return frag
    }

    private fun showFeatureTwo(): Fragment {
        val frag = FeatureFragTwo.newInstance()
        this.activity?.replaceFragmentNoBackStack(frag, replaceableFragmentId, "TWO")
        return frag
    }

    override fun navigateLink() {
        showFeatureOne()
    }

    override fun onDeepLinkBack() {
        //not needed
    }

    override fun route(routeKey: CoordinatorManager.State, navigationData: CoordinatorManager.NavigationData?): Fragment? {
        return when (routeKey) {
            FeatureStates.FEATURE_ONE -> showFeatureOne()
            FeatureStates.FEATURE_TWO -> showFeatureTwo()
            else -> null
        }
    }
}
