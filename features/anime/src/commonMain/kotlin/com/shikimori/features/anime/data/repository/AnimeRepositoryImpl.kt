package com.shikimori.features.anime.data.repository

import com.shikimori.core.domain.model.Anime
import com.shikimori.features.anime.data.remote.AnimeApi
import com.shikimori.features.anime.domain.repository.AnimeRepository

class AnimeRepositoryImpl(
    private val animeApi: AnimeApi
) : AnimeRepository {
    
    override suspend fun getAnimes(
        page: Int,
        limit: Int,
        search: String?
    ): Result<List<Anime>> {
        return try {
            println("🚀 AnimeRepository: Запрос аниме - page=$page, limit=$limit, search=$search")
            val animes = animeApi.getAnimes(
                page = page,
                limit = limit,
                search = search
            )
            println("✅ AnimeRepository: Получено ${animes.size} аниме")
            Result.success(animes)
        } catch (e: Exception) {
            println("❌ AnimeRepository: Ошибка при запросе аниме: ${e.message}")
            println("📋 Детали ошибки: ${e.stackTraceToString()}")
            Result.failure(e)
        }
    }
} 