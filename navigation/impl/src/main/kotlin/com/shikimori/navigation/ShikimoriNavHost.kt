package com.shikimori.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.shikimori.features.home.navigation.homeScreen
import com.shikimori.features.animedetails.navigation.animeDetailsScreen
import com.shikimori.features.mangadetails.navigation.mangaDetailsScreen
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
fun ShikimoriNavHost(
    navController: NavHostController
) {
    // Предоставляем Navigator через Koin
    val navigator: Navigator = koinInject { parametersOf(navController) }
    
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        homeScreen(navigator)
        animeDetailsScreen(navigator)
        mangaDetailsScreen(navigator)
    }
} 