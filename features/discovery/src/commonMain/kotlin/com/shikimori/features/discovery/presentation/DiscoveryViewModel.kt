package com.shikimori.features.discovery.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.shikimori.common.viewmodel.ViewModel
import com.shikimori.core.domain.model.Anime
import com.shikimori.features.discovery.domain.usecase.GetTrendingAnimesUseCase
import kotlinx.coroutines.launch

sealed interface DiscoveryUiState {
    data object Loading : DiscoveryUiState
    data class Success(val trendingAnimes: List<Anime>) : DiscoveryUiState
    data class Error(val message: String) : DiscoveryUiState
}

class DiscoveryViewModel(
    private val getTrendingAnimesUseCase: GetTrendingAnimesUseCase
) : ViewModel() {
    
    private val _uiState = mutableStateOf<DiscoveryUiState>(DiscoveryUiState.Loading)
    val uiState: State<DiscoveryUiState> = _uiState
    
    init {
        loadTrendingAnimes()
    }
    
    private fun loadTrendingAnimes() {
        viewModelScope.launch {
            _uiState.value = DiscoveryUiState.Loading
            
            getTrendingAnimesUseCase()
                .onSuccess { animes ->
                    _uiState.value = DiscoveryUiState.Success(animes)
                }
                .onFailure { exception ->
                    _uiState.value = DiscoveryUiState.Error(
                        message = exception.message ?: "Unknown error"
                    )
                }
        }
    }
    
    fun retry() {
        loadTrendingAnimes()
    }
} 