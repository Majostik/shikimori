package com.shikimori.features.animedetails.data.api

import com.shikimori.core.domain.model.Anime
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class AnimeDetailsApi(
    private val httpClient: HttpClient
) {
    suspend fun getAnimeById(id: Int): Anime {
        return httpClient.get("animes/$id").body()
    }
} 