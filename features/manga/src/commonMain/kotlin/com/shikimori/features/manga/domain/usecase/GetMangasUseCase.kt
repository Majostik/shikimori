package com.shikimori.features.manga.domain.usecase

import com.shikimori.core.domain.model.Manga
import com.shikimori.features.manga.domain.repository.MangaRepository

class GetMangasUseCase(
    private val mangaRepository: MangaRepository
) {
    suspend operator fun invoke(
        page: Int = 1,
        limit: Int = 20
    ): Result<List<Manga>> {
        return mangaRepository.getMangas(page = page, limit = limit, search = null)
    }
} 