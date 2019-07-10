package com.christian.multinavexample

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    private val coordinatorManager = ExampleCoordinatorManager

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
                ExampleDeeplinkIdentifier.OVERVIEW2
            ),
            Pair(
                "detail",
                ExampleDeeplinkIdentifier.DETAIL
            )
        )
    }

}
