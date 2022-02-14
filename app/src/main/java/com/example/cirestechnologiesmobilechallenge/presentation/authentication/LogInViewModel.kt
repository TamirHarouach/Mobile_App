package com.example.cirestechnologiesmobilechallenge.presentation.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cirestechnologiesmobilechallenge.core.util.Constants
import com.example.cirestechnologiesmobilechallenge.domain.model.User
import com.example.cirestechnologiesmobilechallenge.presentation.onBoarding.OnBoardingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogInViewModel: ViewModel() {


    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun signIn(user: User) {
        viewModelScope.launch {
            when(user) {
                Constants.USER_VALID  -> _eventFlow.emit(UIEvent.LogInButtonPressed(Constants.SUCCESS))
                Constants.USER_BLOCKED  -> _eventFlow.emit(UIEvent.LogInButtonPressed(Constants.BLOCKED))
                else ->  _eventFlow.emit(UIEvent.LogInButtonPressed(Constants.ERROR))
            }
        }
    }

    sealed class UIEvent {
        data class LogInButtonPressed(val message: String): UIEvent()
    }
}