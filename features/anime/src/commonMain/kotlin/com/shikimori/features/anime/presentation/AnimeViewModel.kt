package com.shikimori.features.anime.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.shikimori.common.viewmodel.ViewModel
import com.shikimori.core.domain.model.Anime
import com.shikimori.features.anime.domain.usecase.GetAnimesUseCase
import com.shikimori.features.anime.domain.usecase.SearchAnimesUseCase
import kotlinx.coroutines.launch

sealed interface AnimeUiState {
    data object Loading : AnimeUiState
    data class Success(
        val animes: List<Anime>,
        val searchQuery: String = "",
        val isLoadingMore: Boolean = false,
        val canLoadMore: Boolean = true
    ) : AnimeUiState
    data class Error(val message: String) : AnimeUiState
}

class AnimeViewModel(
    private val getAnimesUseCase: GetAnimesUseCase,
    private val searchAnimesUseCase: SearchAnimesUseCase
) : ViewModel() {
    
    private val _uiState = mutableStateOf<AnimeUiState>(AnimeUiState.Loading)
    val uiState: State<AnimeUiState> = _uiState
    
    private var currentPage = 1
    private var currentSearchQuery = ""
    private val pageSize = 20
    
    // Scroll state preservation
    var scrollPosition: Int = 0
        private set
    var scrollOffset: Int = 0
        private set
    
    init {
        loadAnimes()
    }
    
    fun loadAnimes(refresh: Boolean = false) {
        if (refresh) {
            currentPage = 1
            _uiState.value = AnimeUiState.Loading
        }
        
        viewModelScope.launch {
            getAnimesUseCase(page = currentPage, limit = pageSize)
                .onSuccess { animes ->
                    val currentState = _uiState.value as? AnimeUiState.Success
                    val existingAnimes = if (refresh || currentState == null) emptyList() else currentState.animes
                    
                    _uiState.value = AnimeUiState.Success(
                        animes = existingAnimes + animes,
                        searchQuery = currentSearchQuery,
                        isLoadingMore = false,
                        canLoadMore = animes.size == pageSize
                    )
                    
                    if (animes.isNotEmpty()) {
                        currentPage++
                    }
                }
                .onFailure { exception ->
                    _uiState.value = AnimeUiState.Error(
                        message = exception.message ?: "Unknown error"
                    )
                }
        }
    }
    
    fun loadMoreAnimes() {
        val currentState = _uiState.value as? AnimeUiState.Success ?: return
        if (currentState.isLoadingMore || !currentState.canLoadMore) return
        
        _uiState.value = currentState.copy(isLoadingMore = true)
        
        viewModelScope.launch {
            if (currentSearchQuery.isNotBlank()) {
                searchAnimesUseCase(currentSearchQuery, page = currentPage, limit = pageSize)
            } else {
                getAnimesUseCase(page = currentPage, limit = pageSize)
            }.onSuccess { animes ->
                _uiState.value = currentState.copy(
                    animes = currentState.animes + animes,
                    isLoadingMore = false,
                    canLoadMore = animes.size == pageSize
                )
                
                if (animes.isNotEmpty()) {
                    currentPage++
                }
            }.onFailure { exception ->
                _uiState.value = currentState.copy(isLoadingMore = false)
            }
        }
    }
    
    fun searchAnimes(query: String) {
        currentSearchQuery = query
        currentPage = 1
        
        viewModelScope.launch {
            _uiState.value = AnimeUiState.Loading
            
            searchAnimesUseCase(query, page = currentPage, limit = pageSize)
                .onSuccess { animes ->
                    _uiState.value = AnimeUiState.Success(
                        animes = animes,
                        searchQuery = query,
                        isLoadingMore = false,
                        canLoadMore = animes.size == pageSize
                    )
                    
                    if (animes.isNotEmpty()) {
                        currentPage++
                    }
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
        currentPage = 1
        loadAnimes(refresh = true)
    }
    
    fun updateScrollPosition(position: Int, offset: Int) {
        scrollPosition = position
        scrollOffset = offset
    }
} 