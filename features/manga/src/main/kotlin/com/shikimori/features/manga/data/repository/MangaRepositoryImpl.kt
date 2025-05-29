package com.shikimori.features.manga.data.repository

import com.shikimori.core.domain.model.Genre
import com.shikimori.core.domain.model.Image
import com.shikimori.core.domain.model.Manga
import com.shikimori.features.manga.domain.repository.MangaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MangaRepositoryImpl : MangaRepository {
    
    private val mockMangas = listOf(
        Manga(
            id = 1,
            name = "One Piece",
            russian = "Ван Пис",
            image = Image("", "", "", ""),
            url = "",
            score = "9.2",
            genres = listOf(
                Genre(1, "Adventure", "Приключения", "manga"),
                Genre(2, "Comedy", "Комедия", "manga")
            ),
            chapters = 1100,
            volumes = 108,
            status = "ongoing"
        ),
        Manga(
            id = 2,
            name = "Naruto",
            russian = "Наруто",
            image = Image("", "", "", ""),
            url = "",
            score = "8.8",
            genres = listOf(
                Genre(3, "Action", "Экшен", "manga"),
                Genre(4, "Martial Arts", "Боевые искусства", "manga")
            ),
            chapters = 700,
            volumes = 72,
            status = "released"
        ),
        Manga(
            id = 3,
            name = "Attack on Titan",
            russian = "Атака титанов",
            image = Image("", "", "", ""),
            url = "",
            score = "9.0",
            genres = listOf(
                Genre(5, "Drama", "Драма", "manga"),
                Genre(6, "Horror", "Ужасы", "manga")
            ),
            chapters = 139,
            volumes = 34,
            status = "released"
        ),
        Manga(
            id = 4,
            name = "Death Note",
            russian = "Тетрадь смерти",
            image = Image("", "", "", ""),
            url = "",
            score = "9.1",
            genres = listOf(
                Genre(7, "Thriller", "Триллер", "manga"),
                Genre(8, "Supernatural", "Сверхъестественное", "manga")
            ),
            chapters = 108,
            volumes = 12,
            status = "released"
        ),
        Manga(
            id = 5,
            name = "Dragon Ball",
            russian = "Драконий жемчуг",
            image = Image("", "", "", ""),
            url = "",
            score = "8.7",
            genres = listOf(
                Genre(9, "Action", "Экшен", "manga"),
                Genre(10, "Adventure", "Приключения", "manga")
            ),
            chapters = 519,
            volumes = 42,
            status = "released"
        ),
        Manga(
            id = 6,
            name = "My Hero Academia",
            russian = "Моя геройская академия",
            image = Image("", "", "", ""),
            url = "",
            score = "8.5",
            genres = listOf(
                Genre(11, "Superhero", "Супергерои", "manga"),
                Genre(12, "School", "Школа", "manga")
            ),
            chapters = 400,
            volumes = 38,
            status = "ongoing"
        )
    )
    
    override suspend fun getMangas(
        page: Int,
        limit: Int,
        search: String?
    ): Result<List<Manga>> {
        return try {
            val filteredMangas = if (search.isNullOrBlank()) {
                mockMangas
            } else {
                mockMangas.filter { manga ->
                    manga.name.contains(search, ignoreCase = true) ||
                    manga.russian?.contains(search, ignoreCase = true) == true ||
                    manga.genres.any { it.name.contains(search, ignoreCase = true) || it.russian.contains(search, ignoreCase = true) }
                }
            }
            Result.success(filteredMangas)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 