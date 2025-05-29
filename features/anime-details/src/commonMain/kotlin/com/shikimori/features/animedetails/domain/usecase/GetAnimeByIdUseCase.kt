package com.shikimori.features.animedetails.domain.usecase

import com.shikimori.core.domain.model.Anime
import com.shikimori.features.animedetails.domain.repository.AnimeDetailsRepository
import kotlinx.coroutines.flow.first

class GetAnimeByIdUseCase(
    private val animeDetailsRepository: AnimeDetailsRepository
) {
    suspend operator fun invoke(id: Int): Result<Anime> {
        return try {
            val anime = animeDetailsRepository.getAnimeById(id).first()
            if (anime != null) {
                Result.success(anime)
            } else {
                Result.failure(Exception("Anime not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 