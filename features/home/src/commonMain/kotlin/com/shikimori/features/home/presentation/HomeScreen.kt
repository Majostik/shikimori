package com.shikimori.features.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
    val tabs = createTabs()

    Scaffold(
        bottomBar = {
            HomeBottomBar(
                tabs = tabs,
                selectedTabIndex = selectedTabIndex,
                onTabSelected = component::selectTab
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        HomeContent(
            selectedTabIndex = selectedTabIndex,
            component = component,
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
        )
    }
}

@Composable
private fun createTabs(): List<TabItem> {
    return listOf(
        TabItem("Anime", Icons.Default.PlayArrow),
        TabItem("Manga", Icons.Default.Star),
        TabItem("Discovery", Icons.Default.Search),
        TabItem("Settings", Icons.Default.Settings)
    )
}

@Composable
private fun HomeBottomBar(
    tabs: List<TabItem>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        tonalElevation = 8.dp
    ) {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.title,
                        tint = if (selectedTabIndex == index) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                },
                label = {
                    Text(
                        text = tab.title,
                        style = if (selectedTabIndex == index) {
                            MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold
                            )
                        } else {
                            MaterialTheme.typography.labelSmall
                        },
                        color = if (selectedTabIndex == index) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurfaceVariant
                        }
                    )
                },
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }
}

@Composable
private fun HomeContent(
    selectedTabIndex: Int,
    component: HomeComponent,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
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