package com.example.cirestechnologiesmobilechallenge.presentation.news

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
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
    var exit = false

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

    override fun onBackPressed() {
        if (exit) {
            finishAffinity() // finish activity
        } else {
            Toast.makeText(
                this@MainActivity, getString(R.string.exit_message),
                3 * 1000
            ).show()
            exit = true
            Handler(Looper.getMainLooper()).postDelayed(
                { exit = false },
                (3 * 1000).toLong()
            )
        }
    }
}