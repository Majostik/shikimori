package com.shikimori.features.mangadetails.data.repository

import com.shikimori.core.domain.model.Manga
import com.shikimori.core.utils.Logger
import com.shikimori.features.mangadetails.data.api.MangaDetailsApi
import com.shikimori.features.mangadetails.domain.repository.MangaDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MangaDetailsRepositoryImpl(
    private val api: MangaDetailsApi
) : MangaDetailsRepository {
    
    override suspend fun getMangaById(mangaId: Int): Flow<Manga?> = flow {
        Logger.d("MangaDetailsRepo", "🌐 Fetching manga details from API for id: $mangaId")
        val manga = api.getMangaById(mangaId)
        Logger.d("MangaDetailsRepo", "✅ Successfully fetched manga: ${manga.name}")
        emit(manga)
    }
} 