package com.shikimori.features.animedetails.domain.repository

import com.shikimori.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface AnimeDetailsRepository {
    fun getAnimeById(id: Int): Flow<Anime?>
} 