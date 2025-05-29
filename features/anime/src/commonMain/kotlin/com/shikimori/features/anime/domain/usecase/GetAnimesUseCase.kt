package com.shikimori.features.anime.domain.usecase

import com.shikimori.core.domain.model.Anime
import com.shikimori.features.anime.domain.repository.AnimeRepository

class GetAnimesUseCase(
    private val animeRepository: AnimeRepository
) {
    suspend operator fun invoke(
        page: Int = 1,
        limit: Int = 20,
        search: String? = null
    ): Result<List<Anime>> {
        return animeRepository.getAnimes(
            page = page,
            limit = limit,
            search = search
        )
    }
} 