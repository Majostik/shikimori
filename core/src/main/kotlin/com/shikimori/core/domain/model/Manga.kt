package com.shikimori.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Manga(
    val id: Int,
    val name: String,
    val russian: String? = null,
    val image: Image,
    val url: String,
    val kind: String? = null,
    val score: String? = null,
    val status: String? = null,
    val volumes: Int = 0,
    val chapters: Int = 0,
    val airedOn: String? = null,
    val releasedOn: String? = null,
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
    val licensors: List<String> = emptyList(),
    val genres: List<Genre> = emptyList(),
    val publishers: List<Publisher> = emptyList(),
    val userRate: UserRate? = null
)

@Serializable
data class Publisher(
    val id: Int,
    val name: String
) 