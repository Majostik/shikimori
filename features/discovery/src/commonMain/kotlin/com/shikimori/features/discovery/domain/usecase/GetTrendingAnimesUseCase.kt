package com.shikimori.features.discovery.domain.usecase

import com.shikimori.core.domain.model.Anime
import com.shikimori.features.discovery.domain.repository.DiscoveryRepository
import kotlinx.coroutines.flow.first

class GetTrendingAnimesUseCase(
    private val discoveryRepository: DiscoveryRepository
) {
    suspend operator fun invoke(): Result<List<Anime>> {
        return try {
            val animes = discoveryRepository.getTrendingAnimes().first()
            Result.success(animes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 