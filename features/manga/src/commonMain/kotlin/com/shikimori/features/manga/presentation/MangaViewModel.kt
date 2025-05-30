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
        val searchQuery: String = "",
        val isLoadingMore: Boolean = false,
        val canLoadMore: Boolean = true
    ) : MangaUiState
    data class Error(val message: String) : MangaUiState
}

class MangaViewModel(
    private val getMangasUseCase: GetMangasUseCase,
    private val searchMangasUseCase: SearchMangasUseCase
) : ViewModel() {
    
    private val _uiState = mutableStateOf<MangaUiState>(MangaUiState.Loading)
    val uiState: State<MangaUiState> = _uiState
    
    private var currentPage = 1
    private var currentSearchQuery = ""
    private val pageSize = 20
    
    // Scroll state preservation
    var scrollPosition: Int = 0
        private set
    
    init {
        loadMangas()
    }
    
    fun loadMangas(refresh: Boolean = false) {
        if (refresh) {
            currentPage = 1
            _uiState.value = MangaUiState.Loading
        }
        
        viewModelScope.launch {
            getMangasUseCase(page = currentPage, limit = pageSize)
                .onSuccess { mangas ->
                    val currentState = _uiState.value as? MangaUiState.Success
                    val existingMangas = if (refresh || currentState == null) emptyList() else currentState.mangas
                    
                    _uiState.value = MangaUiState.Success(
                        mangas = existingMangas + mangas,
                        searchQuery = currentSearchQuery,
                        isLoadingMore = false,
                        canLoadMore = mangas.size == pageSize
                    )
                    
                    if (mangas.isNotEmpty()) {
                        currentPage++
                    }
                }
                .onFailure { exception ->
                    _uiState.value = MangaUiState.Error(
                        message = exception.message ?: "Unknown error"
                    )
                }
        }
    }
    
    fun loadMoreMangas() {
        val currentState = _uiState.value as? MangaUiState.Success ?: return
        if (currentState.isLoadingMore || !currentState.canLoadMore) return
        
        _uiState.value = currentState.copy(isLoadingMore = true)
        
        viewModelScope.launch {
            if (currentSearchQuery.isNotBlank()) {
                searchMangasUseCase(currentSearchQuery, page = currentPage, limit = pageSize)
            } else {
                getMangasUseCase(page = currentPage, limit = pageSize)
            }.onSuccess { mangas ->
                _uiState.value = currentState.copy(
                    mangas = currentState.mangas + mangas,
                    isLoadingMore = false,
                    canLoadMore = mangas.size == pageSize
                )
                
                if (mangas.isNotEmpty()) {
                    currentPage++
                }
            }.onFailure { exception ->
                _uiState.value = currentState.copy(isLoadingMore = false)
            }
        }
    }
    
    fun searchMangas(query: String) {
        currentSearchQuery = query
        currentPage = 1
        
        viewModelScope.launch {
            _uiState.value = MangaUiState.Loading
            
            searchMangasUseCase(query, page = currentPage, limit = pageSize)
                .onSuccess { mangas ->
                    _uiState.value = MangaUiState.Success(
                        mangas = mangas,
                        searchQuery = query,
                        isLoadingMore = false,
                        canLoadMore = mangas.size == pageSize
                    )
                    
                    if (mangas.isNotEmpty()) {
                        currentPage++
                    }
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
        currentPage = 1
        loadMangas(refresh = true)
    }
    
    fun updateScrollPosition(position: Int) {
        scrollPosition = position
    }
} 