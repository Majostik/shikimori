package com.shikimori.features.discovery.data.repository

import com.shikimori.core.domain.model.Anime
import com.shikimori.core.domain.model.Genre
import com.shikimori.core.domain.model.Image
import com.shikimori.features.discovery.domain.repository.DiscoveryRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DiscoveryRepositoryImpl : DiscoveryRepository {
    
    private val trendingAnimes = listOf(
        Anime(
            id = 1,
            name = "Attack on Titan",
            russian = "Атака титанов",
            image = Image("", "", "", ""),
            url = "",
            score = "9.0",
            genres = listOf(Genre(1, "Action", "Экшен", "anime")),
            episodes = 75,
            episodesAired = 75,
            status = "released"
        ),
        Anime(
            id = 2,
            name = "Jujutsu Kaisen",
            russian = "Магическая битва",
            image = Image("", "", "", ""),
            url = "",
            score = "8.6",
            genres = listOf(Genre(1, "Action", "Экшен", "anime")),
            episodes = 24,
            episodesAired = 24,
            status = "released"
        ),
        Anime(
            id = 3,
            name = "Demon Slayer",
            russian = "Клинок, рассекающий демонов",
            image = Image("", "", "", ""),
            url = "",
            score = "8.7",
            genres = listOf(Genre(1, "Action", "Экшен", "anime")),
            episodes = 44,
            episodesAired = 44,
            status = "released"
        ),
        Anime(
            id = 4,
            name = "One Piece",
            russian = "Ван Пис",
            image = Image("", "", "", ""),
            url = "",
            score = "9.1",
            genres = listOf(Genre(1, "Action", "Экшен", "anime")),
            episodes = 1000,
            episodesAired = 1000,
            status = "ongoing"
        ),
        Anime(
            id = 5,
            name = "Naruto",
            russian = "Наруто",
            image = Image("", "", "", ""),
            url = "",
            score = "8.4",
            genres = listOf(Genre(1, "Action", "Экшен", "anime")),
            episodes = 720,
            episodesAired = 720,
            status = "released"
        ),
        Anime(
            id = 6,
            name = "My Hero Academia",
            russian = "Моя геройская академия",
            image = Image("", "", "", ""),
            url = "",
            score = "8.5",
            genres = listOf(Genre(1, "Action", "Экшен", "anime")),
            episodes = 138,
            episodesAired = 138,
            status = "released"
        ),
        Anime(
            id = 7,
            name = "Death Note",
            russian = "Тетрадь смерти",
            image = Image("", "", "", ""),
            url = "",
            score = "9.0",
            genres = listOf(Genre(4, "Thriller", "Триллер", "anime")),
            episodes = 37,
            episodesAired = 37,
            status = "released"
        ),
        Anime(
            id = 8,
            name = "One Punch Man",
            russian = "Ванпанчмен",
            image = Image("", "", "", ""),
            url = "",
            score = "8.8",
            genres = listOf(Genre(1, "Action", "Экшен", "anime")),
            episodes = 24,
            episodesAired = 24,
            status = "released"
        ),
        Anime(
            id = 9,
            name = "Tokyo Ghoul",
            russian = "Токийский гуль",
            image = Image("", "", "", ""),
            url = "",
            score = "8.0",
            genres = listOf(Genre(5, "Supernatural", "Сверхъестественное", "anime")),
            episodes = 48,
            episodesAired = 48,
            status = "released"
        ),
        Anime(
            id = 10,
            name = "Fullmetal Alchemist",
            russian = "Стальной алхимик",
            image = Image("", "", "", ""),
            url = "",
            score = "9.5",
            genres = listOf(Genre(1, "Action", "Экшен", "anime")),
            episodes = 64,
            episodesAired = 64,
            status = "released"
        )
    )
    
    override fun getTrendingAnimes(): Flow<List<Anime>> = flow {
        delay(500)
        emit(trendingAnimes.take(10))
    }
    
    override fun getPopularAnimes(): Flow<List<Anime>> = flow {
        delay(500)
        emit(trendingAnimes.sortedByDescending { it.score }.take(10))
    }
} 