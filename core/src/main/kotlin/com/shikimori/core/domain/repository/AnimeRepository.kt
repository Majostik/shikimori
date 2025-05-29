package com.shikimori.core.domain.repository

import com.shikimori.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeRepository {
    fun getPopularAnime(): Flow<List<Anime>>
    fun getTrendingAnime(): Flow<List<Anime>>
    fun getAnimeById(id: Int): Flow<Anime?>
    fun searchAnime(query: String): Flow<List<Anime>>
} 