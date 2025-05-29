package com.shikimori.navigation


// Вспомогательные функции для создания маршрутов
object Routes {
    fun animeDetails(animeId: Int): String = "anime_details/$animeId"
    fun mangaDetails(mangaId: Int): String = "manga_details/$mangaId"
    
    const val HOME = "home"
    const val ANIME_DETAILS_PATTERN = "anime_details/{animeId}"
    const val MANGA_DETAILS_PATTERN = "manga_details/{mangaId}"
} 