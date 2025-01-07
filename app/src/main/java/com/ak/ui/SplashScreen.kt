package com.ak.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ak.SharedPref
import com.ak.ui.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Install the system splash screen
        val splashScreen = installSplashScreen()
        // Customize splash screen behavior
        splashScreen.setKeepOnScreenCondition {
            // Example: Keep the splash screen visible while data is loading
            isDataLoading()
        }
        // Check user session or login status
        val isLoggedIn = checkUserLoginStatus()

        // Navigate to the appropriate screen after splash
        val nextActivity = if (isLoggedIn) {
            HomeScreen::class.java
        } else {
            LoginScreen::class.java
        }

        // Start the next activity and finish the splash screen
        startActivity(Intent(this, nextActivity))
        finish()

//        setContentView(R.layout.activity_splash)
//        Handler(Looper.myLooper()!!).postDelayed({
//            startActivity(Intent(this, LoginScreen::class.java))
//            finish()
//        }, 3000)

    }

    private fun isDataLoading(): Boolean {
        return true
    }


    private fun checkUserLoginStatus(): Boolean {
        SharedPref.getInstance(this).userProfile?.let {
            return true
        }
        return false
    }
}