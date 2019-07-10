package com.christian.multinavexample.feature.one.nav

import androidx.fragment.app.Fragment
import com.christian.multinavexample.R
import com.christian.multinavexample.feature.one.OverviewFragment
import com.christian.multinavlib.navigation.coordinator.BaseCoordinatorImpl
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.christian.multinavlib.navigation.deeplink.DeepLink
import com.christian.multinavlib.navigation.extension.replaceFragment

class OneFlowCoordinator : BaseCoordinatorImpl() {
    override var replaceableFragmentId = R.id.fragment_container

    enum class States : CoordinatorManager.State {
        OVERVIEW
    }

    private fun showOverview() {
        this.currentChildFragment = OverviewFragment.newInstance()
        this.currentFeatureFragment?.replaceFragment(
            this.currentChildFragment,
            replaceableFragmentId,
            "OverviewFragment"
        )
    }

    override fun onDeepLinkBack() {
    }

    override fun navigateLink() {
        showOverview()
    }

    override fun navigateDeepLink(deepLink: DeepLink) {
    }

    override fun route(routeKey: CoordinatorManager.State, navigationData: CoordinatorManager.NavigationData?): Fragment? {
        when (routeKey) {
            States.OVERVIEW -> showOverview()
        }
        return null
    }
}