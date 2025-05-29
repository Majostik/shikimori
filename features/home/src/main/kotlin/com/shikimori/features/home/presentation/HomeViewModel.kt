package com.shikimori.features.home.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed interface HomeUiState {
    data class Success(val selectedTabIndex: Int = 0) : HomeUiState
}

class HomeViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Success())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    fun selectTab(index: Int) {
        _uiState.value = HomeUiState.Success(selectedTabIndex = index)
    }
} 