package com.christian.multinavlib.navigation.coordinator

import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.christian.multinavlib.navigation.deeplink.DeepLinkHandler
import com.christian.multinavlib.navigation.deeplink.DeepLinkIdentifier
import java.util.*


@Suppress("unused")
class CoordinatorManager(private val deepLinkHandler: DeepLinkHandler) {
    private val featureCoordinators = HashMap<State, BaseCoordinator>()
    private lateinit var currentFeatureCoordinator: BaseCoordinator
    private lateinit var mainCoordinator: BaseCoordinator
    private lateinit var applicationPartCoordinator: BaseCoordinator

    fun startApplicationRouting(
        fragmentActivity: FragmentActivity,
        data: Uri? = null,
        vararg deepLinkInformation: Pair<String, DeepLinkIdentifier>
    ) {
        data?.let {
            deepLinkInformation.forEach { this.deepLinkHandler.registerDeepLink(it.first, it.second) }
            this.deepLinkHandler.setUri(data)
        }
        this.applicationPartCoordinator.start(fragmentActivity)
    }

    fun switchFeatureCoordinator(featureKey: State, fragment: Fragment) {
        val tempFeatureCoordinator = featureCoordinators[featureKey]
        tempFeatureCoordinator?.let {
            this.currentFeatureCoordinator = it
            this.currentFeatureCoordinator.start(fragment)
        }
    }

    fun registerMainCoordinator(mainCoordinator: BaseCoordinator) {
        this.mainCoordinator = mainCoordinator
    }

    fun registerApplicationPartCoordinator(applicationPartCoordinator: BaseCoordinator) {
        this.applicationPartCoordinator = applicationPartCoordinator
    }

    fun registerFeatureCoordinator(featureKey: State, featureCoordinator: BaseCoordinator) {
        this.featureCoordinators[featureKey] = featureCoordinator
    }

    fun unregisterFeatureCoordinator(featureKey: State) {
        this.featureCoordinators.remove(featureKey)
    }

    fun navigateInFeature(routeKey: State, navigationData: NavigationData? = null) {
        this.currentFeatureCoordinator.route(routeKey, navigationData)
    }

    fun initialNavigateFeature() {
        this.currentFeatureCoordinator.initialNavigation()
    }

    fun backStackFeature() {
        this.currentFeatureCoordinator.back()
    }

    fun navigateToFeature(routeKey: State, navigationData: NavigationData? = null) {
        this.mainCoordinator.route(routeKey, navigationData)
    }

    fun navigateToApplicationPart(routeKey: State) {
        this.applicationPartCoordinator.route(routeKey, null)
    }

    fun startNavigation(
        fragmentActivity: FragmentActivity,
        uri: Uri? = null,
        withInitialNavigation: Boolean? = null
    ) {
        when {
            withInitialNavigation != null -> this.mainCoordinator.start(
                fragmentActivity,
                withInitialNavigation
            )
            uri == null -> this.mainCoordinator.start(fragmentActivity, uri)
            else -> this.mainCoordinator.start(fragmentActivity)
        }
    }

    open class NavigationData(
        open val params: HashMap<String, Any>? = null
    )

    interface State
}