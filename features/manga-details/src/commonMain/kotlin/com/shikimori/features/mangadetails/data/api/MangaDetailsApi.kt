package com.shikimori.features.mangadetails.data.api

import com.shikimori.core.domain.model.Manga
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MangaDetailsApi(
    private val httpClient: HttpClient
) {
    suspend fun getMangaById(id: Int): Manga {
        return httpClient.get("mangas/$id").body()
    }
} 