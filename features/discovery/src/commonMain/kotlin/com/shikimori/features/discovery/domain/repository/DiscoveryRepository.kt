package com.shikimori.features.discovery.domain.repository

import com.shikimori.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface DiscoveryRepository {
    fun getTrendingAnimes(): Flow<List<Anime>>
    fun getPopularAnimes(): Flow<List<Anime>>
} 