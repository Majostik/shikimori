package com.shikimori.features.anime.data.repository

import com.shikimori.core.domain.model.Anime
import com.shikimori.core.domain.model.Genre
import com.shikimori.core.domain.model.Image
import com.shikimori.features.anime.domain.repository.AnimeRepository
import kotlinx.coroutines.delay

class AnimeRepositoryImpl : AnimeRepository {
    
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
        ),
        Anime(
            id = 4,
            name = "Jujutsu Kaisen",
            russian = "Магическая битва",
            image = Image("", "", "", ""),
            url = "",
            score = "8.6",
            genres = listOf(
                Genre(1, "Action", "Экшен", "anime"),
                Genre(5, "Supernatural", "Сверхъестественное", "anime")
            ),
            episodes = 24,
            episodesAired = 24,
            status = "released"
        ),
        Anime(
            id = 5,
            name = "My Hero Academia",
            russian = "Моя геройская академия",
            image = Image("", "", "", ""),
            url = "",
            score = "8.5",
            genres = listOf(
                Genre(1, "Action", "Экшен", "anime"),
                Genre(6, "Superhero", "Супергерои", "anime")
            ),
            episodes = 138,
            episodesAired = 138,
            status = "released"
        ),
        Anime(
            id = 6,
            name = "One Punch Man",
            russian = "Ванпанчмен",
            image = Image("", "", "", ""),
            url = "",
            score = "8.8",
            genres = listOf(
                Genre(1, "Action", "Экшен", "anime"),
                Genre(7, "Comedy", "Комедия", "anime")
            ),
            episodes = 24,
            episodesAired = 24,
            status = "released"
        )
    )
    
    override suspend fun getAnimes(
        page: Int,
        limit: Int,
        search: String?
    ): Result<List<Anime>> {
        return try {
            // Имитируем сетевой запрос
            delay(500)
            
            val filteredAnimes = if (search.isNullOrBlank()) {
                mockAnimes
            } else {
                mockAnimes.filter { anime ->
                    anime.name.contains(search, ignoreCase = true) ||
                    anime.russian?.contains(search, ignoreCase = true) == true
                }
            }
            
            val startIndex = (page - 1) * limit
            val endIndex = minOf(startIndex + limit, filteredAnimes.size)
            
            val paginatedAnimes = if (startIndex < filteredAnimes.size) {
                filteredAnimes.subList(startIndex, endIndex)
            } else {
                emptyList()
            }
            
            Result.success(paginatedAnimes)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 