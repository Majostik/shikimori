package com.shikimori.features.animedetails.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.shikimori.common.viewmodel.ViewModel
import com.shikimori.core.domain.model.Anime
import com.shikimori.features.animedetails.domain.usecase.GetAnimeByIdUseCase
import kotlinx.coroutines.launch

sealed interface AnimeDetailsUiState {
    data object Loading : AnimeDetailsUiState
    data class Success(val anime: Anime) : AnimeDetailsUiState
    data class Error(val message: String) : AnimeDetailsUiState
}

class AnimeDetailsViewModel(
    private val getAnimeByIdUseCase: GetAnimeByIdUseCase
) : ViewModel() {
    
    private val _uiState = mutableStateOf<AnimeDetailsUiState>(AnimeDetailsUiState.Loading)
    val uiState: State<AnimeDetailsUiState> = _uiState
    
    fun loadAnime(animeId: Int) {
        viewModelScope.launch {
            _uiState.value = AnimeDetailsUiState.Loading
            
            getAnimeByIdUseCase(animeId)
                .onSuccess { anime ->
                    _uiState.value = AnimeDetailsUiState.Success(anime)
                }
                .onFailure { exception ->
                    _uiState.value = AnimeDetailsUiState.Error(
                        message = exception.message ?: "Unknown error"
                    )
                }
        }
    }
} 