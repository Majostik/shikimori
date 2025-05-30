package com.shikimori.features.manga.data.remote

import com.shikimori.core.domain.model.Manga
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class MangaApi(private val httpClient: HttpClient) {
    
    suspend fun getMangas(
        page: Int = 1,
        limit: Int = 20,
        order: String = "popularity",
        kind: String? = null,
        status: String? = null,
        season: String? = null,
        score: Int? = null,
        genre: String? = null,
        publisher: String? = null,
        franchise: String? = null,
        censored: Boolean? = null,
        mylist: String? = null,
        ids: String? = null,
        excludeIds: String? = null,
        search: String? = null
    ): List<Manga> {
        return httpClient.get("mangas") {
            parameter("page", page)
            parameter("limit", limit)
            parameter("order", order)
            kind?.let { parameter("kind", it) }
            status?.let { parameter("status", it) }
            season?.let { parameter("season", it) }
            score?.let { parameter("score", it) }
            genre?.let { parameter("genre", it) }
            publisher?.let { parameter("publisher", it) }
            franchise?.let { parameter("franchise", it) }
            censored?.let { parameter("censored", it) }
            mylist?.let { parameter("mylist", it) }
            ids?.let { parameter("ids", it) }
            excludeIds?.let { parameter("exclude_ids", it) }
            search?.let { parameter("search", it) }
        }.body()
    }
} 