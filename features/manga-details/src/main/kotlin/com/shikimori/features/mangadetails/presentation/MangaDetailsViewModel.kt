package com.shikimori.features.mangadetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shikimori.core.domain.model.Manga
import com.shikimori.features.mangadetails.domain.usecase.GetMangaByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface MangaDetailsUiState {
    data object Loading : MangaDetailsUiState
    data class Success(val manga: Manga) : MangaDetailsUiState
    data class Error(val message: String) : MangaDetailsUiState
}

class MangaDetailsViewModel(
    private val getMangaByIdUseCase: GetMangaByIdUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<MangaDetailsUiState>(MangaDetailsUiState.Loading)
    val uiState: StateFlow<MangaDetailsUiState> = _uiState.asStateFlow()
    
    fun loadManga(mangaId: Int) {
        viewModelScope.launch {
            _uiState.value = MangaDetailsUiState.Loading
            
            try {
                getMangaByIdUseCase(mangaId).collect { manga ->
                    _uiState.value = if (manga != null) {
                        MangaDetailsUiState.Success(manga = manga)
                    } else {
                        MangaDetailsUiState.Error(message = "Manga not found")
                    }
                }
            } catch (e: Exception) {
                _uiState.value = MangaDetailsUiState.Error(
                    message = e.message ?: "Unknown error"
                )
            }
        }
    }
} 