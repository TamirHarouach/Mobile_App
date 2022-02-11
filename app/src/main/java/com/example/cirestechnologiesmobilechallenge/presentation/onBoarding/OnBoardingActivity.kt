package com.example.cirestechnologiesmobilechallenge.presentation.onBoarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.cirestechnologiesmobilechallenge.R
import com.example.cirestechnologiesmobilechallenge.databinding.ActivityOnBoardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnBoardingBinding

    private var onBoardingPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            updateCircleMarker(binding, position)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numberOfScreens = resources.getStringArray(R.array.on_boarding_titles).size
        val onBoardingAdapter = OnBoardingAdapter(this, numberOfScreens)
        binding.onBoardingViewPager.adapter = onBoardingAdapter
        binding.onBoardingViewPager.registerOnPageChangeCallback(onBoardingPageChangeCallback)
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

}