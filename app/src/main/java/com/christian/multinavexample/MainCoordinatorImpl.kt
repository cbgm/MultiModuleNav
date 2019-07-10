package com.christian.multinavexample

import androidx.fragment.app.Fragment
import com.christian.multinavexample.feature.one.FeatureFragOne
import com.christian.multinavexample.feature.two.FeatureFragTwo
import com.christian.multinavlib.navigation.coordinator.BaseCoordinatorImpl
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.christian.multinavlib.navigation.deeplink.DeepLink


object MainCoordinatorImpl : BaseCoordinatorImpl() {

    override var replaceableFragmentId: Int = R.id.feature_container


    override fun navigateDeepLink(deepLink: DeepLink) {
        when (deepLink.action) {
            ExampleDeeplinkIdentifier.OVERVIEW2 -> showFeatureTwo()
            else -> showFeatureOne()
        }
    }

    private fun showFeatureOne(): Fragment {
        var frag = FeatureFragOne.newInstance()
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.addToBackStack("ONE")
        fragmentTransaction?.replace(replaceableFragmentId, frag, "ONE")
        fragmentTransaction?.commit()
        return frag
    }

    private fun showFeatureTwo(): Fragment {
        val frag = FeatureFragTwo.newInstance()
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.addToBackStack("TWO")
        fragmentTransaction?.replace(replaceableFragmentId, frag, "TWO")
        fragmentTransaction?.commit()
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
            ExampleCoordinatorManager.States.FEATURE_ONE -> showFeatureOne()
            ExampleCoordinatorManager.States.FEATURE_TWO -> showFeatureTwo()
            else -> null
        }
    }
}
