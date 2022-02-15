package com.example.cirestechnologiesmobilechallenge.presentation.onBoarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.cirestechnologiesmobilechallenge.R
import com.example.cirestechnologiesmobilechallenge.core.util.collectLatestLifecycleFlow
import com.example.cirestechnologiesmobilechallenge.databinding.ActivityOnBoardingBinding
import com.example.cirestechnologiesmobilechallenge.presentation.authentication.LogInActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding
    private val viewModel: OnBoardingViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val numberOfScreens = resources.getStringArray(R.array.on_boarding_titles).size
        initViewPager(numberOfScreens)

        setOnButtonsClickListener()
        viewModelCollector(numberOfScreens)
    }

    private fun viewModelCollector(numberOfScreens: Int) {
        collectLatestLifecycleFlow(viewModel.eventFlow) { event ->
            when(event) {
                is OnBoardingViewModel.UIEvent.NextButtonPressed -> {
                    if (event.position == (numberOfScreens - 1)) {
                        goToLoginPage()
                    } else {
                        binding.onBoardingViewPager.currentItem = (event.position + 1)
                    }
                }

                is OnBoardingViewModel.UIEvent.SkipButtonPressed -> {
                    goToLoginPage()
                }
            }
        }

    }

    private fun goToLoginPage() {
        startActivity(Intent(this, LogInActivity::class.java))
    }



    private fun setOnButtonsClickListener() {
        binding.onBoardNext.setOnClickListener {
            viewModel.onNextButtonPressed()
        }

        binding.onBoardSkip.setOnClickListener {
            viewModel.onSkipButtonPressed()
        }
    }

    private fun initViewPager(numberOfScreens: Int) {
        val onBoardingAdapter = OnBoardingAdapter(this, numberOfScreens)
        viewModel.setNumberOfScreens(numberOfScreens)
        binding.onBoardingViewPager.adapter = onBoardingAdapter
        binding.onBoardingViewPager.registerOnPageChangeCallback(onBoardingPageChangeCallback)
    }

    private var onBoardingPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            updateCircleMarker(binding, position)
            viewModel.onPageSelected(position)
        }
    }

    private fun updateCircleMarker(binding: ActivityOnBoardingBinding, position: Int) {
        when (position) {
            0 -> {
                binding.onBoardingInitialCircle.background = ContextCompat.getDrawable(this, R.drawable.bg_black_circle)
                binding.onBoardingMiddleCircle.background = ContextCompat.getDrawable(this,R.drawable.bg_gray_circle)
                binding.onBoardingLastCircle.background = ContextCompat.getDrawable(this,R.drawable.bg_gray_circle)
            }
            1 -> {
                binding.onBoardingInitialCircle.background = ContextCompat.getDrawable(this,R.drawable.bg_gray_circle)
                binding.onBoardingMiddleCircle.background = ContextCompat.getDrawable(this,R.drawable.bg_black_circle)
                binding.onBoardingLastCircle.background = ContextCompat.getDrawable(this,R.drawable.bg_gray_circle)
            }
            2 -> {
                binding.onBoardingInitialCircle.background = ContextCompat.getDrawable(this,R.drawable.bg_gray_circle)
                binding.onBoardingMiddleCircle.background = ContextCompat.getDrawable(this,R.drawable.bg_gray_circle)
                binding.onBoardingLastCircle.background = ContextCompat.getDrawable(this,R.drawable.bg_black_circle)
            }
        }
    }

    override fun onDestroy() {
        binding.onBoardingViewPager.unregisterOnPageChangeCallback(onBoardingPageChangeCallback)
        super.onDestroy()
    }

    override fun onBackPressed() {
        finishAffinity() // finish activity
    }

}