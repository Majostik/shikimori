package com.shikimori.features.anime.domain.repository

import com.shikimori.core.domain.model.Anime

interface AnimeRepository {
    suspend fun getAnimes(
        page: Int = 1,
        limit: Int = 20,
        search: String? = null
    ): Result<List<Anime>>
} 