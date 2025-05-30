package com.shikimori.features.animedetails.presentation

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.shikimori.core.domain.model.Anime
import com.shikimori.core.domain.model.fullPreview
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import com.shikimori.common.di.koinInject
import com.shikimori.navigation.component.AnimeDetailsComponent

// Design System Components
import com.shikimori.designsystem.components.LoadingState
import com.shikimori.designsystem.components.ErrorState
import com.shikimori.designsystem.components.PosterImage
import com.shikimori.designsystem.components.RatingDisplay
import com.shikimori.designsystem.components.SectionTitle
import com.shikimori.designsystem.components.SectionCard

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AnimeDetailsScreen(
    component: AnimeDetailsComponent,
    viewModel: AnimeDetailsViewModel = koinInject()
) {
    val uiState by viewModel.uiState
    
    LaunchedEffect(component.animeId) {
        viewModel.loadAnime(component.animeId)
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimeDetailsTopBar(
            title = when (val state = uiState) {
                is AnimeDetailsUiState.Success -> state.anime.russian ?: state.anime.name
                else -> "Anime Details"
            },
            onBackClick = component::onBackClicked
        )
        
        AnimeDetailsContent(
            uiState = uiState,
            onRetry = { viewModel.loadAnime(component.animeId) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AnimeDetailsTopBar(
    title: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = { 
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground
        )
    )
}

@Composable
private fun AnimeDetailsContent(
    uiState: AnimeDetailsUiState,
    onRetry: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (uiState) {
            is AnimeDetailsUiState.Loading -> {
                LoadingState(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            
            is AnimeDetailsUiState.Error -> {
                ErrorState(
                    message = uiState.message,
                    onRetry = onRetry,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            
            is AnimeDetailsUiState.Success -> {
                AnimeDetailsSuccess(anime = uiState.anime)
            }
        }
    }
}

@Composable
private fun AnimeDetailsSuccess(anime: Anime) {
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
            .padding(bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AnimeHeader(anime = anime)
        
        if (anime.genres.isNotEmpty()) {
            GenresSection(genres = anime.genres)
        }
        
        DescriptionSection(description = anime.description)
    }
}

@Composable
private fun AnimeHeader(anime: Anime) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        AnimePoster(
            anime = anime,
            modifier = Modifier
                .width(140.dp)
                .height(200.dp)
        )
        
        AnimeInfo(
            anime = anime,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun AnimePoster(
    anime: Anime,
    modifier: Modifier = Modifier
) {
    PosterImage(
        imageUrl = anime.image.fullPreview(),
        contentDescription = anime.name,
        modifier = modifier
    )
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
        AnimeTitles(anime = anime)
        AnimeRating(score = anime.score)
        AnimeStats(anime = anime)
        AnimeStatus(status = anime.status)
    }
}

@Composable
private fun AnimeTitles(anime: Anime) {
    Text(
        text = anime.russian ?: anime.name,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = FontWeight.Bold
    )
    
    if (anime.russian != null && anime.name != anime.russian) {
        Text(
            text = anime.name,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun AnimeRating(score: String?) {
    if (!score.isNullOrBlank()) {
        RatingDisplay(
            rating = score
        )
    }
}

@Composable
private fun AnimeStats(anime: Anime) {
    Text(
        text = "${anime.episodesAired}/${anime.episodes} episodes",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun AnimeStatus(status: String?) {
    Text(
        text = status?.replaceFirstChar { char -> 
            if (char.isLowerCase()) char.titlecase() else char.toString() 
        } ?: "Unknown",
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.primary
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun GenresSection(genres: List<com.shikimori.core.domain.model.Genre>) {
    SectionCard {
        SectionTitle(text = "Genres")
        
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            genres.forEach { genre ->
                AssistChip(
                    onClick = { },
                    label = { Text(genre.russian) }
                )
            }
        }
    }
}

@Composable
private fun DescriptionSection(description: String?) {
    var isDescriptionExpanded by remember { mutableStateOf(false) }
    
    SectionCard {
        SectionTitle(text = "Description")
        
        ExpandableDescription(
            description = description ?: "No description available for this anime.",
            isExpanded = isDescriptionExpanded,
            onToggleExpanded = { isDescriptionExpanded = !isDescriptionExpanded }
        )
    }
}

@Composable
private fun ExpandableDescription(
    description: String,
    isExpanded: Boolean,
    onToggleExpanded: () -> Unit
) {
    val shortDescription = if (description.length > 200) {
        description.take(200) + "..."
    } else {
        description
    }
    
    Text(
        text = if (isExpanded) description else shortDescription,
        style = MaterialTheme.typography.bodyMedium,
        lineHeight = MaterialTheme.typography.bodyMedium.lineHeight
    )
    
    if (description.length > 200) {
        TextButton(
            onClick = onToggleExpanded,
            modifier = Modifier.padding(0.dp),
            contentPadding = PaddingValues(horizontal = 0.dp, vertical = 4.dp)
        ) {
            Text(
                text = if (isExpanded) "Скрыть" else "Показать полностью",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
} 