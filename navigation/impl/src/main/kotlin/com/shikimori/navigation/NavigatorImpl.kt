package com.shikimori.navigation

import androidx.navigation.NavController

class NavigatorImpl(
    private val navController: NavController
) : Navigator {
    
    override fun navigateToAnimeDetails(animeId: Int) {
        navController.navigate(Routes.animeDetails(animeId))
    }
    
    override fun navigateToMangaDetails(mangaId: Int) {
        navController.navigate(Routes.mangaDetails(mangaId))
    }
    
    override fun navigateToHome() {
        navController.navigate(Routes.HOME) {
            popUpTo(Routes.HOME) { inclusive = true }
        }
    }
    
    override fun navigateBack() {
        navController.popBackStack()
    }
} 