package com.shikimori.features.discovery.domain.usecase

import com.shikimori.core.domain.model.Anime
import com.shikimori.features.discovery.domain.repository.DiscoveryRepository
import kotlinx.coroutines.flow.Flow

class GetTrendingAnimesUseCase(
    private val discoveryRepository: DiscoveryRepository
) {
    operator fun invoke(): Flow<List<Anime>> {
        return discoveryRepository.getTrendingAnimes()
    }
} 