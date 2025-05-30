package com.shikimori.features.anime.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shikimori.core.domain.model.Anime
import com.shikimori.core.domain.model.fullPreview
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import com.shikimori.common.di.koinInject
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.background
import androidx.compose.runtime.Stable
import kotlinx.coroutines.delay
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// Design System Components
import com.shikimori.designsystem.components.LoadingState
import com.shikimori.designsystem.components.ErrorState
import com.shikimori.designsystem.components.EmptyState
import com.shikimori.designsystem.components.SearchField
import com.shikimori.designsystem.components.RatingOverlay
import com.shikimori.designsystem.components.PosterImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeScreen(
    onAnimeClick: (Int) -> Unit = {},
    viewModel: AnimeViewModel = koinInject()
) {
    val uiState by viewModel.uiState
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }
    val gridState = rememberLazyGridState()
    
    // Proper debounce implementation
    val scope = rememberCoroutineScope()
    var searchJob by remember { mutableStateOf<Job?>(null) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchTopBar(
            isSearchActive = isSearchActive,
            searchQuery = searchQuery,
            onSearchActiveChange = { isSearchActive = it },
            onSearchQueryChange = { newQuery ->
                searchQuery = newQuery
                
                // Cancel previous search job
                searchJob?.cancel()
                
                // Start new debounced search
                searchJob = scope.launch {
                    delay(500) // 500ms debounce
                    if (newQuery.isNotBlank()) {
                        viewModel.searchAnimes(newQuery)
                    } else {
                        viewModel.clearSearch()
                    }
                }
            },
            onClearSearch = {
                searchQuery = ""
                isSearchActive = false
                searchJob?.cancel()
                viewModel.clearSearch()
            },
            title = "Anime",
            placeholder = "Search anime..."
        )
        
        AnimeContent(
            uiState = uiState,
            gridState = gridState,
            onAnimeClick = onAnimeClick,
            onRetry = { viewModel.loadAnimes() },
            onLoadMore = { viewModel.loadMoreAnimes() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Stable
@Composable
private fun AnimeItem(
    anime: Anime,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
    ) {
        Column {
            AnimeImage(
                anime = anime,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            )
            
            AnimeInfo(
                anime = anime,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
private fun AnimeImage(
    anime: Anime,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        PosterImage(
            imageUrl = anime.image.fullPreview(),
            contentDescription = anime.name,
            modifier = Modifier.fillMaxSize()
        )
        
        if (!anime.score.isNullOrBlank()) {
            RatingOverlay(
                rating = anime.score!!,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            )
        }
    }
}

@Composable
private fun AnimeInfo(
    anime: Anime,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = anime.russian ?: anime.name,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            minLines = 2
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = anime.genres.firstOrNull()?.russian ?: "Аниме",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchTopBar(
    isSearchActive: Boolean,
    searchQuery: String,
    onSearchActiveChange: (Boolean) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onClearSearch: () -> Unit,
    title: String,
    placeholder: String
) {
    TopAppBar(
        title = { 
            if (isSearchActive) {
                SearchField(
                    query = searchQuery,
                    onQueryChange = onSearchQueryChange,
                    placeholder = placeholder,
                    onClear = {
                        onClearSearch()
                        onSearchActiveChange(false)
                    },
                    modifier = Modifier.padding(end = 8.dp)
                )
            } else {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        actions = {
            if (!isSearchActive) {
                IconButton(
                    onClick = { onSearchActiveChange(true) }
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            } else {
                IconButton(
                    onClick = { 
                        onClearSearch()
                        onSearchActiveChange(false)
                    }
                ) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "Close search",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}

@Composable
private fun AnimeContent(
    uiState: AnimeUiState,
    gridState: LazyGridState,
    onAnimeClick: (Int) -> Unit,
    onRetry: () -> Unit,
    onLoadMore: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is AnimeUiState.Loading -> {
                LoadingState(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            
            is AnimeUiState.Error -> {
                ErrorState(
                    message = uiState.message,
                    onRetry = onRetry,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            
            is AnimeUiState.Success -> {
                if (uiState.animes.isEmpty()) {
                    EmptyState(
                        message = if (uiState.searchQuery.isNotBlank()) "No anime found" else "No anime available",
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    AnimeGrid(
                        animes = uiState.animes,
                        gridState = gridState,
                        isLoadingMore = uiState.isLoadingMore,
                        canLoadMore = uiState.canLoadMore,
                        onAnimeClick = onAnimeClick,
                        onLoadMore = onLoadMore
                    )
                }
            }
        }
    }
}

@Composable
private fun AnimeGrid(
    animes: List<Anime>,
    gridState: LazyGridState,
    isLoadingMore: Boolean,
    canLoadMore: Boolean,
    onAnimeClick: (Int) -> Unit,
    onLoadMore: () -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = gridState,
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        flingBehavior = ScrollableDefaults.flingBehavior()
    ) {
        itemsIndexed(
            items = animes,
            key = { _, anime -> anime.id }
        ) { index, anime ->
            if (index >= animes.size - 2 && canLoadMore && !isLoadingMore) {
                onLoadMore()
            }
            
            AnimeItem(
                anime = anime,
                onClick = { onAnimeClick(anime.id) }
            )
        }
        
        if (isLoadingMore) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                LoadingMoreIndicator()
            }
        }
    }
}

@Composable
private fun LoadingMoreIndicator() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
} 