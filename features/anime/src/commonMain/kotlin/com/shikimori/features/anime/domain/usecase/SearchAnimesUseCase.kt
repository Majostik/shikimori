package com.shikimori.features.anime.domain.usecase

import com.shikimori.core.domain.model.Anime
import com.shikimori.features.anime.domain.repository.AnimeRepository

class SearchAnimesUseCase(
    private val animeRepository: AnimeRepository
) {
    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        limit: Int = 20
    ): Result<List<Anime>> {
        // Бизнес-логика: не ищем, если запрос слишком короткий
        if (query.length < 2) {
            return Result.success(emptyList())
        }
        
        return animeRepository.getAnimes(
            page = page,
            limit = limit,
            search = query.trim()
        )
    }
} 