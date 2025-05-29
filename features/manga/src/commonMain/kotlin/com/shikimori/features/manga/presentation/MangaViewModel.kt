package com.shikimori.features.manga.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.shikimori.common.viewmodel.ViewModel
import com.shikimori.core.domain.model.Manga
import com.shikimori.features.manga.domain.usecase.GetMangasUseCase
import com.shikimori.features.manga.domain.usecase.SearchMangasUseCase
import kotlinx.coroutines.launch

sealed interface MangaUiState {
    data object Loading : MangaUiState
    data class Success(
        val mangas: List<Manga>,
        val searchQuery: String = ""
    ) : MangaUiState
    data class Error(val message: String) : MangaUiState
}

class MangaViewModel(
    private val getMangasUseCase: GetMangasUseCase,
    private val searchMangasUseCase: SearchMangasUseCase
) : ViewModel() {
    
    private val _uiState = mutableStateOf<MangaUiState>(MangaUiState.Loading)
    val uiState: State<MangaUiState> = _uiState
    
    private var currentSearchQuery = ""
    
    init {
        loadMangas()
    }
    
    fun loadMangas() {
        viewModelScope.launch {
            _uiState.value = MangaUiState.Loading
            
            getMangasUseCase()
                .onSuccess { mangas ->
                    _uiState.value = MangaUiState.Success(
                        mangas = mangas,
                        searchQuery = currentSearchQuery
                    )
                }
                .onFailure { exception ->
                    _uiState.value = MangaUiState.Error(
                        message = exception.message ?: "Unknown error"
                    )
                }
        }
    }
    
    fun searchMangas(query: String) {
        currentSearchQuery = query
        
        viewModelScope.launch {
            _uiState.value = MangaUiState.Loading
            
            searchMangasUseCase(query)
                .onSuccess { mangas ->
                    _uiState.value = MangaUiState.Success(
                        mangas = mangas,
                        searchQuery = query
                    )
                }
                .onFailure { exception ->
                    _uiState.value = MangaUiState.Error(
                        message = exception.message ?: "Search error"
                    )
                }
        }
    }
    
    fun clearSearch() {
        currentSearchQuery = ""
        loadMangas()
    }
} 