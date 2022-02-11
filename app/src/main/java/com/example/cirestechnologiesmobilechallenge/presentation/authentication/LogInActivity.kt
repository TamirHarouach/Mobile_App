package com.example.cirestechnologiesmobilechallenge.presentation.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cirestechnologiesmobilechallenge.databinding.ActivityLogInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}