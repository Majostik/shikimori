package com.shikimori.features.mangadetails.domain.usecase

import com.shikimori.core.domain.model.Manga
import com.shikimori.features.mangadetails.domain.repository.MangaDetailsRepository
import kotlinx.coroutines.flow.first

class GetMangaByIdUseCase(
    private val mangaDetailsRepository: MangaDetailsRepository
) {
    suspend operator fun invoke(mangaId: Int): Result<Manga> {
        return try {
            val manga = mangaDetailsRepository.getMangaById(mangaId).first()
            if (manga != null) {
                Result.success(manga)
            } else {
                Result.failure(Exception("Manga not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 