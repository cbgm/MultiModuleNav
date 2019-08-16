A lib for easy navigation in a multi module app.

## Step 1: Define possible deeplinks

    enum class ExampleDeepLinkIdentifier: DeepLinkIdentifier {
        DETAIL {
            override fun hasParameter() = true
        },
        OVERVIEW2 {
        override fun hasParameter() = false
        }
    }

## Step 2: set up coordinators and navigate in app
    
    Please take a look into the delivered example app for further explanation
    Annotate FeatureCoordinators, MainCoordinator and RootCoordinator by using either
    
    @RootCoordinator
    @MainCoordinator
    @FeatureCoordinator(state = x)
    
    state mirrors the main navigation pages (not more than 5)
    
## Example feature coordinator
    @FeatureCoordinator(state = 0)
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
    
## Step 3: start app routing and set possible deep links

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
    
    
## Step 4: Init Lib

   After build of project init in application by calling 
   
    MulitNav().init()

