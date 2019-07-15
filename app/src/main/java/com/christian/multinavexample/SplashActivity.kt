package com.christian.multinavexample

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.christian.multinavexample.core.nav.ExampleDeepLinkIdentifier
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import com.christian.multinavlib.ui.CoordinatedActivity
import org.koin.android.ext.android.inject

class SplashActivity : CoordinatedActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val data: Uri? = intent?.data
        //Step 3 start app routing
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
    }
}
