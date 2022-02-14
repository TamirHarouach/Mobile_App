package com.example.cirestechnologiesmobilechallenge.presentation.authentication

import app.cash.turbine.test
import com.example.cirestechnologiesmobilechallenge.core.util.Constants
import kotlinx.coroutines.runBlocking
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After

import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LogInViewModelTest {

    private lateinit var viewModel: LogInViewModel
    lateinit var value : LogInViewModel.UIEvent

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
    fun`checkLogIn, properly check if the user is correct`() = runBlocking {
        viewModel.signIn(Constants.USER_VALID)
        val job = launch {
            assertThat(viewModel.eventFlow.collect()).isEqualTo(LogInViewModel.UIEvent.LogInButtonPressed(Constants.SUCCESS))
        }
        job.cancel()

    }
}