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

    /**
     * Method to start up the application routing (usually called by the splash)
     * @param fragmentActivity The current app part (splash activity)
     * @param data The possible deep link data (optional)
     * @param deepLinkInformation The defined deep links (optional)
     *
     */
    fun startApplicationRouting(
        fragmentActivity: FragmentActivity,
        data: Uri? = null,
        vararg deepLinkInformation: Pair<String, DeepLinkIdentifier>
    ) {
        data?.let {
            deepLinkInformation.forEach {
                this.deepLinkHandler.registerDeepLink(it.first, it.second)
            }
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

    /**
     * Method to register the app part main coordinator (jumping between features).
     * @param mainCoordinator The coordinator
     */
    fun registerMainCoordinator(mainCoordinator: BaseCoordinator) {
        this.mainCoordinator = mainCoordinator
    }

    /**
     * Method to register the app part coordinator (jumping between app parts).
     * @param applicationPartCoordinator The coordinator
     */
    fun registerApplicationPartCoordinator(applicationPartCoordinator: BaseCoordinator) {
        this.applicationPartCoordinator = applicationPartCoordinator
    }

    /**
     * Method to register a feature coordinator (jumping inside feature).
     * @param featureCoordinator The coordinator
     */
    fun registerFeatureCoordinator(featureKey: State, featureCoordinator: BaseCoordinator) {
        this.featureCoordinators[featureKey] = featureCoordinator
    }

    fun unregisterFeatureCoordinator(featureKey: State) {
        this.featureCoordinators.remove(featureKey)
    }

    /**
     * Method should be called to navigate inside a feature.
     * @param routeKey The key to navigate to
     * @param navigationData The possible data which is needed to start up te fragment (arguments data)
     */
    fun navigateInFeature(routeKey: State, navigationData: NavigationData? = null) {
        this.currentFeatureCoordinator.route(routeKey, navigationData)
    }

    /**
     * Method can be called when initial navigation was skipped on start().
     */
    fun initialNavigateFeature() {
        this.currentFeatureCoordinator.initialNavigation()
    }

    /**
     * Method to trigger back navigation.
     */
    fun backStackFeature() {
        this.currentFeatureCoordinator.back()
    }

    /**
     * Method should be called to navigate to a feature.
     * @param routeKey The key to navigate to
     * @param navigationData The possible data which is needed to start up te fragment (arguments data)
     */
    fun navigateToFeature(routeKey: State, navigationData: NavigationData? = null) {
        this.mainCoordinator.route(routeKey, navigationData)
    }

    /**
     * Method should be called to navigate to application part.
     * @param routeKey The key to navigate to
     */
    fun navigateToApplicationPart(routeKey: State) {
        this.applicationPartCoordinator.route(routeKey, null)
    }


    /**
     * Method to start app part navigation
     * @param fragmentActivity The app part (splash, main,...)
     * @param uri The possible deep link data
     * @param withInitialNavigation When initial navigation should be triggered (splash)
     */
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