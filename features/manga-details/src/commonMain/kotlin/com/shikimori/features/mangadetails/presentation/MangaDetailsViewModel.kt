package com.shikimori.features.mangadetails.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.shikimori.common.viewmodel.ViewModel
import com.shikimori.core.domain.model.Manga
import com.shikimori.features.mangadetails.domain.usecase.GetMangaByIdUseCase
import kotlinx.coroutines.launch

sealed interface MangaDetailsUiState {
    data object Loading : MangaDetailsUiState
    data class Success(val manga: Manga) : MangaDetailsUiState
    data class Error(val message: String) : MangaDetailsUiState
}

class MangaDetailsViewModel(
    private val getMangaByIdUseCase: GetMangaByIdUseCase
) : ViewModel() {
    
    private val _uiState = mutableStateOf<MangaDetailsUiState>(MangaDetailsUiState.Loading)
    val uiState: State<MangaDetailsUiState> = _uiState
    
    fun loadManga(mangaId: Int) {
        viewModelScope.launch {
            _uiState.value = MangaDetailsUiState.Loading
            
            getMangaByIdUseCase(mangaId)
                .onSuccess { manga ->
                    _uiState.value = MangaDetailsUiState.Success(manga)
                }
                .onFailure { exception ->
                    _uiState.value = MangaDetailsUiState.Error(
                        message = exception.message ?: "Unknown error"
                    )
                }
        }
    }
} 