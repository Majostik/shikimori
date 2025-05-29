package com.shikimori.features.mangadetails.domain.usecase

import com.shikimori.core.domain.model.Manga
import com.shikimori.features.mangadetails.domain.repository.MangaDetailsRepository
import kotlinx.coroutines.flow.Flow

class GetMangaByIdUseCase(
    private val mangaDetailsRepository: MangaDetailsRepository
) {
    suspend operator fun invoke(mangaId: Int): Flow<Manga?> {
        return mangaDetailsRepository.getMangaById(mangaId)
    }
} 