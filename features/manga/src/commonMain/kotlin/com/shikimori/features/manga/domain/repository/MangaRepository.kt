package com.shikimori.features.manga.domain.repository

import com.shikimori.core.domain.model.Manga

interface MangaRepository {
    suspend fun getMangas(
        page: Int = 1,
        limit: Int = 20,
        search: String? = null
    ): Result<List<Manga>>
} 