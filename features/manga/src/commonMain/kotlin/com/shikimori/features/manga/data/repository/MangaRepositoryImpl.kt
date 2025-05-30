package com.shikimori.features.manga.data.repository

import com.shikimori.core.domain.model.Manga
import com.shikimori.core.utils.Logger
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
            Logger.d("MangaRepository", "🚀 Запрос манги - page=$page, limit=$limit, search=$search")
            val mangas = mangaApi.getMangas(
                page = page,
                limit = limit,
                search = search
            )
            Logger.d("MangaRepository", "✅ Получено ${mangas.size} манги")
            Result.success(mangas)
        } catch (e: Exception) {
            Logger.e("MangaRepository", "❌ Ошибка при запросе манги: ${e.message}", e)
            Logger.d("MangaRepository", "📋 Детали ошибки: ${e.stackTraceToString()}")
            Result.failure(e)
        }
    }
} 