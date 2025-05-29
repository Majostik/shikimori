package com.shikimori.navigation

// Базовый навигатор
interface BaseNavigator {
    fun navigateBack()
    fun navigateToHome()
}

// Навигатор для аниме
interface AnimeNavigator {
    fun navigateToAnimeDetails(animeId: Int)
}

// Навигатор для манги
interface MangaNavigator {
    fun navigateToMangaDetails(mangaId: Int)
}

// Общий навигатор (композиция всех навигаторов)
interface Navigator : BaseNavigator, AnimeNavigator, MangaNavigator 