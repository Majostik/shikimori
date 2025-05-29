package com.shikimori.features.mangadetails.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shikimori.features.mangadetails.presentation.MangaDetailsScreen
import com.shikimori.navigation.Navigator
import com.shikimori.navigation.Routes

const val MANGA_DETAILS_ROUTE = Routes.MANGA_DETAILS_PATTERN

fun mangaDetailsRoute(mangaId: Int) = Routes.mangaDetails(mangaId)

fun NavGraphBuilder.mangaDetailsScreen(navigator: Navigator) {
    composable(MANGA_DETAILS_ROUTE) { backStackEntry ->
        val mangaId = backStackEntry.arguments?.getString("mangaId")?.toIntOrNull() ?: 0
        MangaDetailsScreen(mangaId = mangaId, navigator = navigator)
    }
} 