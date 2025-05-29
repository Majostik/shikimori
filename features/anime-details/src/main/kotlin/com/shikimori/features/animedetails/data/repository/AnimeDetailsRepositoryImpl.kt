package com.shikimori.features.animedetails.data.repository

import com.shikimori.core.domain.model.Anime
import com.shikimori.core.domain.model.Genre
import com.shikimori.core.domain.model.Image
import com.shikimori.features.animedetails.domain.repository.AnimeDetailsRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AnimeDetailsRepositoryImpl : AnimeDetailsRepository {
    
    private val mockAnimes = listOf(
        Anime(
            id = 1,
            name = "Attack on Titan",
            russian = "Атака титанов",
            image = Image("", "", "", ""),
            url = "",
            score = "9.0",
            genres = listOf(
                Genre(1, "Action", "Экшен", "anime"),
                Genre(2, "Drama", "Драма", "anime"),
                Genre(3, "Fantasy", "Фэнтези", "anime")
            ),
            episodes = 75,
            episodesAired = 75,
            status = "released"
        ),
        Anime(
            id = 2,
            name = "Death Note",
            russian = "Тетрадь смерти",
            image = Image("", "", "", ""),
            url = "",
            score = "9.0",
            genres = listOf(
                Genre(4, "Thriller", "Триллер", "anime"),
                Genre(5, "Supernatural", "Сверхъестественное", "anime")
            ),
            episodes = 37,
            episodesAired = 37,
            status = "released"
        ),
        Anime(
            id = 3,
            name = "Demon Slayer",
            russian = "Клинок, рассекающий демонов",
            image = Image("", "", "", ""),
            url = "",
            score = "8.7",
            genres = listOf(
                Genre(1, "Action", "Экшен", "anime"),
                Genre(5, "Supernatural", "Сверхъестественное", "anime")
            ),
            episodes = 44,
            episodesAired = 44,
            status = "released"
        )
    )
    
    override fun getAnimeById(id: Int): Flow<Anime?> = flow {
        delay(300)
        emit(mockAnimes.find { it.id == id })
    }
} 