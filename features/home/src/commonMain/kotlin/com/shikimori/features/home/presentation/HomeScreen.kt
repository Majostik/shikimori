package com.shikimori.features.home.presentation

import com.shikimori.designsystem.ShikimoriTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.shikimori.features.anime.presentation.AnimeScreen
import com.shikimori.features.manga.presentation.MangaScreen
import com.shikimori.features.discovery.presentation.DiscoveryScreen
import com.shikimori.features.settings.presentation.SettingsScreen
import com.shikimori.navigation.component.HomeComponent
import com.shikimori.common.di.koinInject

data class TabItem(
    val title: String,
    val icon: ImageVector
)

@Composable
fun HomeScreen(
    component: HomeComponent,
    viewModel: HomeViewModel = koinInject()
) {
    val uiState by viewModel.uiState
    
    val tabs = listOf(
        TabItem("Anime", Icons.Default.PlayArrow),
        TabItem("Manga", Icons.Default.Star),
        TabItem("Discovery", Icons.Default.Search),
        TabItem("Settings", Icons.Default.Settings)
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                tonalElevation = 0.dp
            ) {
                tabs.forEachIndexed { index, tab ->
                    NavigationBarItem(
                        icon = { 
                            Icon(
                                imageVector = tab.icon, 
                                contentDescription = tab.title
                            ) 
                        },
                        label = { Text(tab.title) },
                        selected = uiState.selectedTabIndex == index,
                        onClick = { viewModel.selectTab(index) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            when (uiState.selectedTabIndex) {
                0 -> AnimeScreen(
                    onAnimeClick = component::onAnimeClicked
                )
                1 -> MangaScreen(
                    onMangaClick = component::onMangaClicked
                )
                2 -> DiscoveryScreen(
                    onAnimeClick = component::onAnimeClicked
                )
                3 -> SettingsScreen()
            }
        }
    }
} 