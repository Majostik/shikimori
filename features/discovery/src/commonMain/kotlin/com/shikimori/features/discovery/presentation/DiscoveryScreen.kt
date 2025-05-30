package com.shikimori.features.discovery.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.shikimori.core.domain.model.Anime
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import com.shikimori.common.di.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoveryScreen(
    onAnimeClick: (Int) -> Unit = {},
    viewModel: DiscoveryViewModel = koinInject()
) {
    val uiState by viewModel.uiState

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        DiscoveryTopBar()
        
        DiscoveryContent(
            uiState = uiState,
            onAnimeClick = onAnimeClick,
            onRetry = { viewModel.retry() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DiscoveryTopBar() {
    TopAppBar(
        title = { Text("Discovery") },
        actions = {
            IconButton(onClick = { /* TODO: Search */ }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
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
private fun DiscoveryContent(
    uiState: DiscoveryUiState,
    onAnimeClick: (Int) -> Unit,
    onRetry: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is DiscoveryUiState.Loading -> {
                LoadingState(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            
            is DiscoveryUiState.Error -> {
                ErrorState(
                    message = uiState.message,
                    onRetry = onRetry,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            
            is DiscoveryUiState.Success -> {
                if (uiState.trendingAnimes.isEmpty()) {
                    EmptyState(
                        message = "No trending anime available",
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    TrendingSection(
                        animes = uiState.trendingAnimes,
                        onAnimeClick = onAnimeClick
                    )
                }
            }
        }
    }
}

@Composable
private fun LoadingState(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(modifier = modifier)
}

@Composable
private fun ErrorState(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

@Composable
private fun EmptyState(
    message: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = message,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
private fun TrendingSection(
    animes: List<Anime>,
    onAnimeClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SectionHeader(text = "Trending Now")
        
        TrendingList(
            animes = animes,
            onAnimeClick = onAnimeClick
        )
        
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun SectionHeader(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
    )
}

@Composable
private fun TrendingList(
    animes: List<Anime>,
    onAnimeClick: (Int) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(animes) { index, anime ->
            DiscoveryItem(
                anime = anime,
                onClick = { onAnimeClick(anime.id) },
                modifier = Modifier.padding(
                    start = if (index == 0) 16.dp else 0.dp,
                    end = if (index == animes.lastIndex) 16.dp else 0.dp
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DiscoveryItem(
    anime: Anime,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .width(160.dp)
            .height(320.dp)
    ) {
        Column {
            DiscoveryItemImage(
                anime = anime,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )
            
            DiscoveryItemInfo(
                anime = anime,
                modifier = Modifier
                    .padding(8.dp)
                    .height(92.dp)
            )
        }
    }
}

@Composable
private fun DiscoveryItemImage(
    anime: Anime,
    modifier: Modifier = Modifier
) {
    KamelImage(
        resource = asyncPainterResource(anime.image.preview),
        contentDescription = anime.name,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun DiscoveryItemInfo(
    anime: Anime,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = anime.russian ?: anime.name,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        
        Text(
            text = anime.genres.firstOrNull()?.russian ?: "",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        
        if (!anime.score.isNullOrBlank()) {
            Text(
                text = "★ ${anime.score}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
} 