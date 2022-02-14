package com.example.cirestechnologiesmobilechallenge.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.cirestechnologiesmobilechallenge.R
import com.example.cirestechnologiesmobilechallenge.presentation.authentication.LogInActivity
import com.example.cirestechnologiesmobilechallenge.presentation.news.MainActivity
import com.example.cirestechnologiesmobilechallenge.presentation.onBoarding.OnBoardingActivity
import com.example.cirestechnologiesmobilechallenge.presentation.onBoarding.OnBoardingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoutingActivity : AppCompatActivity() {
    private val viewModel: OnBoardingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Using The SplashScreen API
        initSplashScreen()
        setContentView(R.layout.activity_routing)
    }

    private fun initSplashScreen() {
        installSplashScreen().apply {
            setOnExitAnimationListener{
                if (!viewModel.goToOnBoarding.value) goToLoginPage()
                else goToOnBoardingPage()
            }
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
    }

    private fun goToLoginPage() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun goToOnBoardingPage() {
        startActivity(Intent(this, OnBoardingActivity::class.java))
    }
}