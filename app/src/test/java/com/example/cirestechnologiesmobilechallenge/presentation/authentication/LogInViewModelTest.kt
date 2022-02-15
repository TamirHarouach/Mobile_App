package com.example.cirestechnologiesmobilechallenge.presentation.authentication

import app.cash.turbine.test
import com.example.cirestechnologiesmobilechallenge.core.util.Constants
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LogInViewModelTest {

    private lateinit var viewModel: LogInViewModel
    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = LogInViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun`checkLogIn, properly check if the user is correct`() = runBlockingTest {
        val job = launch {
            viewModel.eventFlow.test {
                val emission = awaitItem()
                assertThat(emission).isEqualTo(LogInViewModel.UIEvent.LogInButtonPressed(Constants.SUCCESS))
                cancelAndConsumeRemainingEvents()
            }
        }
        viewModel.signIn(Constants.USER_VALID)
        job.join()
        job.cancel()

    }

    @Test
    fun`checkLogIn, properly check if the user is blocked`() = runBlockingTest {
        val job = launch {
            viewModel.eventFlow.test {
                val emission = awaitItem()
                assertThat(emission).isEqualTo(LogInViewModel.UIEvent.LogInButtonPressed(Constants.BLOCKED))
                cancelAndConsumeRemainingEvents()
            }
        }
        viewModel.signIn(Constants.USER_BLOCKED)
        job.join()
        job.cancel()

    }
}