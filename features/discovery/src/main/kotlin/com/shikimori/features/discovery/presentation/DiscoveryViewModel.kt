package com.shikimori.features.discovery.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shikimori.core.domain.model.Anime
import com.shikimori.features.discovery.domain.usecase.GetTrendingAnimesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface DiscoveryUiState {
    data object Loading : DiscoveryUiState
    data class Success(val trendingAnimes: List<Anime>) : DiscoveryUiState
    data class Error(val message: String) : DiscoveryUiState
}

class DiscoveryViewModel(
    private val getTrendingAnimesUseCase: GetTrendingAnimesUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<DiscoveryUiState>(DiscoveryUiState.Loading)
    val uiState: StateFlow<DiscoveryUiState> = _uiState.asStateFlow()
    
    init {
        loadTrendingAnimes()
    }
    
    private fun loadTrendingAnimes() {
        viewModelScope.launch {
            _uiState.value = DiscoveryUiState.Loading
            
            try {
                getTrendingAnimesUseCase().collect { animes ->
                    _uiState.value = DiscoveryUiState.Success(trendingAnimes = animes)
                }
            } catch (e: Exception) {
                _uiState.value = DiscoveryUiState.Error(
                    message = e.message ?: "Unknown error"
                )
            }
        }
    }
    
    fun retry() {
        loadTrendingAnimes()
    }
} 