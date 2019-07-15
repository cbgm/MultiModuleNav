A lib for easy navigation in a multi module app.

## Step 1: define and inject coordinators in application

    private val coordinatorManager: CoordinatorManager by inject()
    private val mainCoordinatorImpl: MainCoordinatorImpl by inject()
    private val twoFlowCoordinator: TwoFlowCoordinator by inject()
    private val rootFlowCoordinatorImpl: RootFlowCoordinatorImpl by inject()
    private val oneFlowCoordinator: OneFlowCoordinator by inject()
   
## Step 2: register coordinators

    coordinatorManager.run {
        //coordinator for application parts "activities" like splash, (auth), main
        registerApplicationPartCoordinator(rootFlowCoordinatorImpl)
        //coordinator for the main application part
        registerMainCoordinator(mainCoordinatorImpl)
        //all features in main (also it is possible to auth as feature in here)
        registerFeatureCoordinator(
            MainCoordinatorImpl.States.FEATURE_ONE,
            oneFlowCoordinator
        )
        registerFeatureCoordinator(
            MainCoordinatorImpl.States.FEATURE_TWO,
            twoFlowCoordinator
        )
    }
   
## Step 3: start app routing and define possible deep links
  
    val data: Uri? = intent?.data
    coordinatorManager.startApplicationRouting(
        this,
        data,
        //Deeplinks
        Pair(
          "overview2",
          ExampleDeepLinkIdentifier.OVERVIEW2
        ), //adb shell am start -W -a android.intent.action.VIEW -d "http://mnl.de://overview2" com.christian.multinavexample
        Pair(
          "detail",
          ExampleDeepLinkIdentifier.DETAIL
        )//adb shell am start -W -a android.intent.action.VIEW -d "http://mnl.de://overview2/detail/{value}" com.christian.multinavexample
    )
    
## Step 4: set up coordinators and navigate in app
    
    Please take a look into the delivered example app for further explanation
    
## Example feature coordinator
    
    class OneFlowCoordinator : BaseCoordinatorImpl() {
        //has to be overriden to set the actual fragment container
        override var replaceableFragmentId = R.id.fragment_container
        
        //states of this feature
        enum class States : CoordinatorManager.State {
            OVERVIEW
        }
        
        //actual navigation (currentChildFragment needs to be used)
        private fun showOverview() {
            this.currentChildFragment = OverviewFragment.newInstance()
            this.currentFeatureFragment?.replaceFragment(
                this.currentChildFragment,
                replaceableFragmentId,
                "OverviewFragment"
            )
        }
        
        //what needs to be done when deep link is active and back button is triggered
        override fun onDeepLinkBack() {
        }
        
        //call navigation to starting page here
        override fun navigateLink() {
            showOverview()
        }

        //define navigation when deep link was found
        override fun navigateDeepLink(deepLink: DeepLink) {
        }

        //actual state routing
        override fun route(routeKey: CoordinatorManager.State, navigationData: CoordinatorManager.NavigationData?): Fragment? {
            when (routeKey) {
                States.OVERVIEW -> showOverview()
            }
            return null
        }
    }
