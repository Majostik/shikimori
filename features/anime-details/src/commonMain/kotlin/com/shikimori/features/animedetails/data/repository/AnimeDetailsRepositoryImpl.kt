package com.shikimori.features.animedetails.data.repository

import com.shikimori.core.domain.model.Anime
import com.shikimori.core.utils.Logger
import com.shikimori.features.animedetails.data.api.AnimeDetailsApi
import com.shikimori.features.animedetails.domain.repository.AnimeDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AnimeDetailsRepositoryImpl(
    private val api: AnimeDetailsApi
) : AnimeDetailsRepository {
    
    override fun getAnimeById(id: Int): Flow<Anime?> = flow {
        try {
            Logger.d("AnimeDetailsRepo", "🌐 Fetching anime details from API for id: $id")
            val anime = api.getAnimeById(id)
            Logger.d("AnimeDetailsRepo", "✅ Successfully fetched anime: ${anime.name}")
            emit(anime)
        } catch (e: Exception) {
            Logger.e("AnimeDetailsRepo", "❌ Error fetching anime details: ${e.message}", e)
            Logger.d("AnimeDetailsRepo", "📋 Error details: ${e.stackTraceToString()}")
        }
    }
} 