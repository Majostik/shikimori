package com.shikimori.features.animedetails.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shikimori.features.animedetails.presentation.AnimeDetailsScreen
import com.shikimori.navigation.Navigator
import com.shikimori.navigation.Routes

const val ANIME_DETAILS_ROUTE = Routes.ANIME_DETAILS_PATTERN

fun animeDetailsRoute(animeId: Int) = Routes.animeDetails(animeId)

fun NavGraphBuilder.animeDetailsScreen(navigator: Navigator) {
    composable(ANIME_DETAILS_ROUTE) { backStackEntry ->
        val animeId = backStackEntry.arguments?.getString("animeId")?.toIntOrNull() ?: 0
        AnimeDetailsScreen(animeId = animeId, navigator = navigator)
    }
} 