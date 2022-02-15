package com.example.cirestechnologiesmobilechallenge.presentation.onBoarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cirestechnologiesmobilechallenge.core.util.Constants
import com.example.cirestechnologiesmobilechallenge.core.util.SharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel@Inject constructor(
    private val sharedPreference: SharedPreference
): ViewModel() {

    private var numberOfScreens = 0
    private var selectedPage = 0

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _goToOnBoarding = MutableStateFlow(true)
    val goToOnBoarding = _goToOnBoarding.asStateFlow()

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        viewModelScope.launch {
//            delay(3000)
            _goToOnBoarding.value = sharedPreference.getValueBoolean(Constants.SHOW_ON_BOARDING, true)
            _isLoading.value = false
        }
    }

    fun setNumberOfScreens(number : Int) {
        numberOfScreens = number
    }

    fun onSkipButtonPressed() {
        viewModelScope.launch {
            sharedPreference.save(Constants.SHOW_ON_BOARDING, false)
            _eventFlow.emit(UIEvent.SkipButtonPressed(numberOfScreens - 1))
        }
    }

    fun onNextButtonPressed() {
        viewModelScope.launch {
            if (selectedPage == numberOfScreens - 1)  sharedPreference.save(Constants.SHOW_ON_BOARDING, false)
            _eventFlow.emit(UIEvent.NextButtonPressed(selectedPage))
        }
    }

    fun onPageSelected(position: Int) {
        selectedPage = position
    }

    sealed class UIEvent {
        data class NextButtonPressed(val position: Int): UIEvent()
        data class SkipButtonPressed(val position: Int): UIEvent()
    }
}