package com.shikimori.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Anime(
    val id: Int,
    val name: String,
    val russian: String? = null,
    val image: Image,
    val url: String,
    val kind: String? = null,
    val score: String? = null,
    val status: String? = null,
    val episodes: Int = 0,
    val episodesAired: Int = 0,
    val airedOn: String? = null,
    val releasedOn: String? = null,
    val rating: String? = null,
    val english: List<String> = emptyList(),
    val japanese: List<String> = emptyList(),
    val synonyms: List<String> = emptyList(),
    val licenseNameRu: String? = null,
    val description: String? = null,
    val descriptionHtml: String? = null,
    val descriptionSource: String? = null,
    val franchise: String? = null,
    val favoured: Boolean = false,
    val anons: Boolean = false,
    val ongoing: Boolean = false,
    val threadId: Int? = null,
    val topicId: Int? = null,
    val myanimelistId: Int? = null,
    val ratesScoresStats: List<RateScore> = emptyList(),
    val ratesStatusesStats: List<RateStatus> = emptyList(),
    val updatedAt: String? = null,
    val nextEpisodeAt: String? = null,
    val fansubbers: List<String> = emptyList(),
    val fandubbers: List<String> = emptyList(),
    val licensors: List<String> = emptyList(),
    val genres: List<Genre> = emptyList(),
    val studios: List<Studio> = emptyList(),
    val videos: List<Video> = emptyList(),
    val screenshots: List<Screenshot> = emptyList(),
    val userRate: UserRate? = null
)

@Serializable
data class Image(
    val original: String,
    val preview: String,
    val x96: String,
    val x48: String
)

// Extension functions to get full URLs
fun Image.fullOriginal(): String = if (original.startsWith("http")) original else "https://shikimori.one$original"
fun Image.fullPreview(): String = if (preview.startsWith("http")) preview else "https://shikimori.one$preview"
fun Image.fullX96(): String = if (x96.startsWith("http")) x96 else "https://shikimori.one$x96"
fun Image.fullX48(): String = if (x48.startsWith("http")) x48 else "https://shikimori.one$x48"

@Serializable
data class RateScore(
    val name: Int,
    val value: Int
)

@Serializable
data class RateStatus(
    val name: String,
    val value: Int
)

@Serializable
data class Genre(
    val id: Int,
    val name: String,
    val russian: String,
    val kind: String
)

@Serializable
data class Studio(
    val id: Int,
    val name: String,
    val filteredName: String? = null,
    val real: Boolean,
    val image: String? = null
)

@Serializable
data class Video(
    val id: Int,
    val url: String,
    val imageUrl: String? = null,
    val playerUrl: String? = null,
    val name: String? = null,
    val kind: String,
    val hosting: String
)

@Serializable
data class Screenshot(
    val original: String,
    val preview: String
)

@Serializable
data class UserRate(
    val id: Int,
    val score: Int,
    val status: String,
    val text: String? = null,
    val episodes: Int,
    val chapters: Int,
    val volumes: Int,
    val textHtml: String,
    val rewatches: Int,
    val createdAt: String,
    val updatedAt: String
) 