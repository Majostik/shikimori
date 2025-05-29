package com.shikimori.features.animedetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shikimori.core.domain.model.Anime
import com.shikimori.features.animedetails.domain.usecase.GetAnimeByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface AnimeDetailsUiState {
    data object Loading : AnimeDetailsUiState
    data class Success(val anime: Anime) : AnimeDetailsUiState
    data class Error(val message: String) : AnimeDetailsUiState
}

class AnimeDetailsViewModel(
    private val getAnimeByIdUseCase: GetAnimeByIdUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<AnimeDetailsUiState>(AnimeDetailsUiState.Loading)
    val uiState: StateFlow<AnimeDetailsUiState> = _uiState.asStateFlow()
    
    fun loadAnime(animeId: Int) {
        viewModelScope.launch {
            _uiState.value = AnimeDetailsUiState.Loading
            
            try {
                getAnimeByIdUseCase(animeId).collect { anime ->
                    _uiState.value = if (anime != null) {
                        AnimeDetailsUiState.Success(anime = anime)
                    } else {
                        AnimeDetailsUiState.Error(message = "Anime not found")
                    }
                }
            } catch (e: Exception) {
                _uiState.value = AnimeDetailsUiState.Error(
                    message = e.message ?: "Unknown error"
                )
            }
        }
    }
} 