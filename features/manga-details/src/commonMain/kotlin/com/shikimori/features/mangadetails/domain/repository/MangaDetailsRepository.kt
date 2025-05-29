package com.shikimori.features.mangadetails.domain.repository

import com.shikimori.core.domain.model.Manga
import kotlinx.coroutines.flow.Flow

interface MangaDetailsRepository {
    suspend fun getMangaById(mangaId: Int): Flow<Manga?>
} 