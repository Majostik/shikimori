package com.shikimori.features.home.presentation

import com.shikimori.designsystem.ShikimoriTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.shikimori.features.anime.presentation.AnimeScreen
import com.shikimori.features.manga.presentation.MangaScreen
import com.shikimori.features.discovery.presentation.DiscoveryScreen
import com.shikimori.features.settings.presentation.SettingsScreen
import com.shikimori.navigation.component.HomeComponent

data class TabItem(
    val title: String,
    val icon: ImageVector
)

@Composable
fun HomeScreen(
    component: HomeComponent
) {
    val selectedTabIndex by component.selectedTabIndex.subscribeAsState()
    
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
                        selected = selectedTabIndex == index,
                        onClick = { component.selectTab(index) },
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
            when (selectedTabIndex) {
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