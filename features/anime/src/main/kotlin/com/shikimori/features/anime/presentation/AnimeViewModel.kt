package com.shikimori.features.anime.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shikimori.core.domain.model.Anime
import com.shikimori.features.anime.domain.usecase.GetAnimesUseCase
import com.shikimori.features.anime.domain.usecase.SearchAnimesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface AnimeUiState {
    data object Loading : AnimeUiState
    data class Success(
        val animes: List<Anime>,
        val searchQuery: String = ""
    ) : AnimeUiState
    data class Error(val message: String) : AnimeUiState
}

class AnimeViewModel(
    private val getAnimesUseCase: GetAnimesUseCase,
    private val searchAnimesUseCase: SearchAnimesUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<AnimeUiState>(AnimeUiState.Loading)
    val uiState: StateFlow<AnimeUiState> = _uiState.asStateFlow()
    
    private var currentSearchQuery = ""
    
    init {
        loadAnimes()
    }
    
    fun loadAnimes() {
        viewModelScope.launch {
            _uiState.value = AnimeUiState.Loading
            
            getAnimesUseCase()
                .onSuccess { animes ->
                    _uiState.value = AnimeUiState.Success(
                        animes = animes,
                        searchQuery = currentSearchQuery
                    )
                }
                .onFailure { exception ->
                    _uiState.value = AnimeUiState.Error(
                        message = exception.message ?: "Unknown error"
                    )
                }
        }
    }
    
    fun searchAnimes(query: String) {
        currentSearchQuery = query
        
        viewModelScope.launch {
            _uiState.value = AnimeUiState.Loading
            
            searchAnimesUseCase(query)
                .onSuccess { animes ->
                    _uiState.value = AnimeUiState.Success(
                        animes = animes,
                        searchQuery = query
                    )
                }
                .onFailure { exception ->
                    _uiState.value = AnimeUiState.Error(
                        message = exception.message ?: "Search error"
                    )
                }
        }
    }
    
    fun clearSearch() {
        currentSearchQuery = ""
        loadAnimes()
    }
} 