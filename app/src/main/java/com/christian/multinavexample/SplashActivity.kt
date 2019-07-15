package com.christian.multinavexample

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.christian.multinavexample.core.nav.ExampleDeepLinkIdentifier
import com.christian.multinavlib.navigation.coordinator.CoordinatorManager
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {
    private val coordinatorManager: CoordinatorManager by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        val data: Uri? = intent?.data
        coordinatorManager.startApplicationRouting(
            this,
            data,
            Pair(
                "overview2",
                ExampleDeepLinkIdentifier.OVERVIEW2
            ),
            Pair(
                "detail",
                ExampleDeepLinkIdentifier.DETAIL
            )
        )
    }
}
