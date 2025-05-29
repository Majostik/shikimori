package com.shikimori.features.manga.data.repository

import com.shikimori.core.domain.model.Manga
import com.shikimori.features.manga.data.remote.MangaApi
import com.shikimori.features.manga.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MangaRepositoryImpl(
    private val mangaApi: MangaApi
) : MangaRepository {
    
    override suspend fun getMangas(
        page: Int,
        limit: Int,
        search: String?
    ): Result<List<Manga>> {
        return try {
            println("🚀 MangaRepository: Запрос манги - page=$page, limit=$limit, search=$search")
            val mangas = mangaApi.getMangas(
                page = page,
                limit = limit,
                search = search
            )
            println("✅ MangaRepository: Получено ${mangas.size} манги")
            Result.success(mangas)
        } catch (e: Exception) {
            println("❌ MangaRepository: Ошибка при запросе манги: ${e.message}")
            println("📋 Детали ошибки: ${e.stackTraceToString()}")
            Result.failure(e)
        }
    }
} 