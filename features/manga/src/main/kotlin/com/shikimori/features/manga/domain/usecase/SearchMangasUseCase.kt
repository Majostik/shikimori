package com.shikimori.features.manga.domain.usecase

import com.shikimori.core.domain.model.Manga
import com.shikimori.features.manga.domain.repository.MangaRepository

class SearchMangasUseCase(
    private val mangaRepository: MangaRepository
) {
    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        limit: Int = 20
    ): Result<List<Manga>> {
        return mangaRepository.getMangas(
            page = page,
            limit = limit,
            search = query.takeIf { it.isNotBlank() }
        )
    }
} 