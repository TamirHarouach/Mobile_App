package com.example.cirestechnologiesmobilechallenge.presentation.news

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cirestechnologiesmobilechallenge.R
import com.example.cirestechnologiesmobilechallenge.databinding.ActivityMainBinding
import com.example.cirestechnologiesmobilechallenge.presentation.news.discover.DiscoverFragment
import com.example.cirestechnologiesmobilechallenge.presentation.news.home.HomeFragment
import com.example.cirestechnologiesmobilechallenge.presentation.news.profile.ProfileFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initBottomBar()
    }

    private fun initBottomBar() {
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    item.isCheckable = true
                    setCurrentFragment(HomeFragment())
                }
                R.id.discover -> {
                    item.isCheckable = true
                    setCurrentFragment(DiscoverFragment())
                }
                R.id.profile -> {
                    item.isCheckable = true
                    setCurrentFragment(ProfileFragment())
                }
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
}