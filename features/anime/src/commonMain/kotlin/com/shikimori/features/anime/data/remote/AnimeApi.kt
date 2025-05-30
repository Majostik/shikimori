package com.shikimori.features.anime.data.remote

import com.shikimori.core.domain.model.Anime
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class AnimeApi(private val httpClient: HttpClient) {
    
    suspend fun getAnimes(
        page: Int = 1,
        limit: Int = 20,
        order: String = "popularity",
        kind: String? = null,
        status: String? = null,
        season: String? = null,
        score: Int? = null,
        duration: String? = null,
        rating: String? = null,
        genre: String? = null,
        studio: String? = null,
        franchise: String? = null,
        censored: Boolean? = null,
        mylist: String? = null,
        ids: String? = null,
        excludeIds: String? = null,
        search: String? = null
    ): List<Anime> {
        return httpClient.get("animes") {
            parameter("page", page)
            parameter("limit", limit)
            parameter("order", order)
            kind?.let { parameter("kind", it) }
            status?.let { parameter("status", it) }
            season?.let { parameter("season", it) }
            score?.let { parameter("score", it) }
            duration?.let { parameter("duration", it) }
            rating?.let { parameter("rating", it) }
            genre?.let { parameter("genre", it) }
            studio?.let { parameter("studio", it) }
            franchise?.let { parameter("franchise", it) }
            censored?.let { parameter("censored", it) }
            mylist?.let { parameter("mylist", it) }
            ids?.let { parameter("ids", it) }
            excludeIds?.let { parameter("exclude_ids", it) }
            search?.let { parameter("search", it) }
        }.body()
    }
} 