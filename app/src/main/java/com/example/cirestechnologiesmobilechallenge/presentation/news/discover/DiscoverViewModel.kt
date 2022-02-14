package com.example.cirestechnologiesmobilechallenge.presentation.news.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cirestechnologiesmobilechallenge.core.util.Constants
import com.example.cirestechnologiesmobilechallenge.core.util.Resource
import com.example.cirestechnologiesmobilechallenge.core.util.SharedPreference
import com.example.cirestechnologiesmobilechallenge.data.remote.dto.NewsDto
import com.example.cirestechnologiesmobilechallenge.data.remote.repository.NewsServices
import com.example.cirestechnologiesmobilechallenge.domain.model.Data
import com.example.cirestechnologiesmobilechallenge.domain.model.User
import com.example.cirestechnologiesmobilechallenge.presentation.onBoarding.OnBoardingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel@Inject constructor(
    private val newsServices: NewsServices
): ViewModel() {

    private val _newsFlow = MutableSharedFlow<List<Data>>()
    val newsFlow = _newsFlow.asSharedFlow()

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

//    init {
//        getNewsByCategory(Constants.Categories.values()[0].value)
//    }

    fun getNewsByCategory(category: String) {
        viewModelScope.launch {
           newsServices.getNewsByCategory(category)
               .onEach { result ->
               when(result) {
                   is Resource.Success -> {
                       _newsFlow.emit(result.data?.dataDto!!.map { it.toData() })
                       _eventFlow.emit(UIEvent.ShowLoading(false))
                   }
                   is Resource.Error -> {
                       _newsFlow.emit(emptyList())
                       _eventFlow.emit(UIEvent.ShowToast(result.message ?: "Unknown error"))
                       _eventFlow.emit(UIEvent.ShowLoading(false))
                   }
                   is Resource.Loading -> {
                       _newsFlow.emit(emptyList())
                       _eventFlow.emit(UIEvent.ShowLoading(true))
                   }
               }
           }.launchIn(this)
        }
    }

    sealed class UIEvent {
        data class ShowToast(val message: String): UIEvent()
        data class ShowLoading(val show: Boolean): UIEvent()
    }
}