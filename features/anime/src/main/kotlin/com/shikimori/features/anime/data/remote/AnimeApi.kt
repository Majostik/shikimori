package com.shikimori.features.anime.data.remote

import com.shikimori.core.domain.model.Anime
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeApi {
    
    @GET("animes")
    suspend fun getAnimes(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20,
        @Query("order") order: String = "popularity",
        @Query("kind") kind: String? = null,
        @Query("status") status: String? = null,
        @Query("season") season: String? = null,
        @Query("score") score: Int? = null,
        @Query("duration") duration: String? = null,
        @Query("rating") rating: String? = null,
        @Query("genre") genre: String? = null,
        @Query("studio") studio: String? = null,
        @Query("franchise") franchise: String? = null,
        @Query("censored") censored: Boolean? = null,
        @Query("mylist") mylist: String? = null,
        @Query("ids") ids: String? = null,
        @Query("exclude_ids") excludeIds: String? = null,
        @Query("search") search: String? = null
    ): List<Anime>
} 