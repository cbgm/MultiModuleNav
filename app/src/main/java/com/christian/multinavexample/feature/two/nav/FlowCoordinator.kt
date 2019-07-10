package com.christian.multinavexample.feature.two.nav

import androidx.fragment.app.Fragment
import com.christian.multinavexample.ExampleDeeplinkIdentifier
import com.christian.multinavexample.R
import com.christian.multinavexample.feature.two.DetailFragment
import com.christian.multinavexample.feature.two.MenuFragment
import com.christian.multinavexample.feature.two.OverviewFragment
import com.christian.multinavexample.feature.two.OverviewTwoFragment
import com.christian.multinavlib.navigation.coordinator.BaseCoordinatorImpl
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.christian.multinavlib.navigation.deeplink.DeepLink

object FlowCoordinator : BaseCoordinatorImpl() {
    override var replaceableFragmentId = R.id.fragment_container

    enum class States : CoordinatorManager.State {
        MENU,
        OVERVIEW,
        OVERVIEW2,
        DETAIL
    }

    private fun showMenu() {
        this.currentChildFragment = MenuFragment.newInstance()
        val fragmentTransaction = currentFeatureFragment?.childFragmentManager?.beginTransaction()
        fragmentTransaction?.addToBackStack("MenuFragment")
        fragmentTransaction?.replace(replaceableFragmentId, this.currentChildFragment, "")
        fragmentTransaction?.commit()
    }

    private fun showOverview() {
        this.currentChildFragment = OverviewFragment.newInstance()
        val fragmentTransaction = currentFeatureFragment?.childFragmentManager?.beginTransaction()
        fragmentTransaction?.addToBackStack("OverviewFragment")
        fragmentTransaction?.replace(replaceableFragmentId, this.currentChildFragment, "")
        fragmentTransaction?.commit()
    }

    private fun showOverview2() {
        this.currentChildFragment = OverviewTwoFragment.newInstance()
        val fragmentTransaction = currentFeatureFragment?.childFragmentManager?.beginTransaction()
        fragmentTransaction?.addToBackStack("Overview2")
        fragmentTransaction?.replace(replaceableFragmentId, this.currentChildFragment, "")
        fragmentTransaction?.commit()
    }

    private fun showDetail(test: String) {
        this.currentChildFragment = DetailFragment.newInstance()
        val fragmentTransaction = currentFeatureFragment?.childFragmentManager?.beginTransaction()
        fragmentTransaction?.addToBackStack("Detail")
        fragmentTransaction?.replace(replaceableFragmentId, this.currentChildFragment, "")
        fragmentTransaction?.commit()
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
            ExampleDeeplinkIdentifier.DETAIL -> showDetail(deepLink.parameter!!)
        }
        isDeepLinkActive = true
    }

    override fun route(routeKey: CoordinatorManager.State, navigationData: CoordinatorManager.NavigationData?): Fragment? {
        when (routeKey) {
            States.MENU -> showMenu()
            States.OVERVIEW -> showOverview()
            States.OVERVIEW2 -> showOverview2()
            States.DETAIL -> showDetail("")
        }
        return null
    }
}