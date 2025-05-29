package com.shikimori.features.animedetails.domain.usecase

import com.shikimori.core.domain.model.Anime
import com.shikimori.features.animedetails.domain.repository.AnimeDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetAnimeByIdUseCase(
    private val animeDetailsRepository: AnimeDetailsRepository
) {
    operator fun invoke(id: Int): Flow<Anime?> {
        return animeDetailsRepository.getAnimeById(id)
    }
} 