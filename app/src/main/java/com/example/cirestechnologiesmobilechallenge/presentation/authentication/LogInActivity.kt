package com.example.cirestechnologiesmobilechallenge.presentation.authentication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.cirestechnologiesmobilechallenge.R
import com.example.cirestechnologiesmobilechallenge.core.util.Constants
import com.example.cirestechnologiesmobilechallenge.core.util.collectLatestLifecycleFlow
import com.example.cirestechnologiesmobilechallenge.databinding.ActivityLogInBinding
import com.example.cirestechnologiesmobilechallenge.domain.model.User
import com.example.cirestechnologiesmobilechallenge.presentation.news.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    private val viewModel: LogInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setOnClickListenerToButtons()
        viewModelCollector()
    }

    private fun setOnClickListenerToButtons() {
        binding.logInButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val user = User(username = username, password = password)
            viewModel.signIn(user)
        }
    }

    private fun viewModelCollector() {
        collectLatestLifecycleFlow(viewModel.eventFlow) { event ->
            when(event) {
                is LogInViewModel.UIEvent.LogInButtonPressed -> {
                    when(event.message) {
                        Constants.SUCCESS -> { goToMainPage() }
                        Constants.BLOCKED -> { Toast.makeText(this, getString(R.string.blocked_account), Toast.LENGTH_LONG).show() }
                        Constants.ERROR -> { Toast.makeText(this, getString(R.string.wrong_account), Toast.LENGTH_LONG).show() }
                    }
                }
            }
        }
    }

    private fun goToMainPage() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onBackPressed() {
        finishAffinity() // finish activity
    }
}