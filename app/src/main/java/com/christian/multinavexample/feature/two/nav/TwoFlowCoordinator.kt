package com.christian.multinavexample.feature.two.nav

import androidx.fragment.app.Fragment
import com.christian.multinavexample.core.nav.ExampleDeepLinkIdentifier
import com.christian.multinavexample.R
import com.christian.multinavexample.feature.two.DetailFragment
import com.christian.multinavexample.feature.two.MenuFragment
import com.christian.multinavexample.feature.two.OverviewFragment
import com.christian.multinavexample.feature.two.OverviewTwoFragment
import com.christian.multinavlib.navigation.coordinator.BaseCoordinatorImpl
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.christian.multinavlib.navigation.deeplink.DeepLink
import com.christian.multinavlib.navigation.extension.replaceFragment

class TwoFlowCoordinator : BaseCoordinatorImpl() {
    override var replaceableFragmentId = R.id.fragment_container

    enum class States : CoordinatorManager.State {
        MENU,
        OVERVIEW,
        OVERVIEW2,
        DETAIL
    }

    private fun showMenu() {
        this.currentChildFragment = MenuFragment.newInstance()
        this.currentFeatureFragment?.replaceFragment(
            this.currentChildFragment,
            replaceableFragmentId,
            "MenuFragment"
        )
    }

    private fun showOverview() {
        this.currentChildFragment = OverviewFragment.newInstance()
        this.currentFeatureFragment?.replaceFragment(
            this.currentChildFragment,
            replaceableFragmentId,
            "OverviewFragment"
        )
    }

    private fun showOverview2() {
        this.currentChildFragment = OverviewTwoFragment.newInstance()
        this.currentFeatureFragment?.replaceFragment(
            this.currentChildFragment,
            replaceableFragmentId,
            "OverviewFragment2"
        )
    }

    private fun showDetail(test: String) {
        this.currentChildFragment = DetailFragment.newInstance(test)
        this.currentFeatureFragment?.replaceFragment(
            this.currentChildFragment,
            replaceableFragmentId,
            "Detail"
        )
    }

    override fun onDeepLinkBack() {
        when (this.currentChildFragment) {
            is DetailFragment -> showOverview2()
            is OverviewTwoFragment -> showMenu()
            else -> currentFeatureFragment?.activity?.finish()
        }
    }

    override fun navigateLink() {
        showMenu()
    }

    override fun navigateDeepLink(deepLink: DeepLink) {
        when (deepLink.action) {
            ExampleDeepLinkIdentifier.DETAIL -> showDetail(deepLink.parameter!!)
        }
        isDeepLinkActive = true
    }

    override fun route(routeKey: CoordinatorManager.State, navigationData: CoordinatorManager.NavigationData?): Fragment? {
        when (routeKey) {
            States.MENU -> showMenu()
            States.OVERVIEW -> showOverview()
            States.OVERVIEW2 -> showOverview2()
            States.DETAIL -> navigationData?.let {
                showDetail(
                    it.params!!["test"].toString()
                )
            }
        }
        return null
    }
}