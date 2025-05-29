package com.shikimori.features.mangadetails.data.repository

import com.shikimori.core.domain.model.Genre
import com.shikimori.core.domain.model.Image
import com.shikimori.core.domain.model.Manga
import com.shikimori.features.mangadetails.domain.repository.MangaDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MangaDetailsRepositoryImpl : MangaDetailsRepository {
    
    private val mockMangas = mapOf(
        1 to Manga(
            id = 1,
            name = "One Piece",
            russian = "Ван Пис",
            image = Image("", "", "", ""),
            url = "",
            score = "9.2",
            genres = listOf(
                Genre(1, "Adventure", "Приключения", "manga"),
                Genre(2, "Comedy", "Комедия", "manga"),
                Genre(3, "Drama", "Драма", "manga")
            ),
            chapters = 1100,
            volumes = 108,
            status = "ongoing"
        ),
        2 to Manga(
            id = 2,
            name = "Naruto",
            russian = "Наруто",
            image = Image("", "", "", ""),
            url = "",
            score = "8.8",
            genres = listOf(
                Genre(4, "Action", "Экшен", "manga"),
                Genre(5, "Martial Arts", "Боевые искусства", "manga"),
                Genre(6, "Supernatural", "Сверхъестественное", "manga")
            ),
            chapters = 700,
            volumes = 72,
            status = "released"
        ),
        3 to Manga(
            id = 3,
            name = "Attack on Titan",
            russian = "Атака титанов",
            image = Image("", "", "", ""),
            url = "",
            score = "9.0",
            genres = listOf(
                Genre(7, "Drama", "Драма", "manga"),
                Genre(8, "Horror", "Ужасы", "manga"),
                Genre(9, "Action", "Экшен", "manga")
            ),
            chapters = 139,
            volumes = 34,
            status = "released"
        ),
        4 to Manga(
            id = 4,
            name = "Death Note",
            russian = "Тетрадь смерти",
            image = Image("", "", "", ""),
            url = "",
            score = "9.1",
            genres = listOf(
                Genre(10, "Thriller", "Триллер", "manga"),
                Genre(11, "Supernatural", "Сверхъестественное", "manga"),
                Genre(12, "Psychological", "Психологический", "manga")
            ),
            chapters = 108,
            volumes = 12,
            status = "released"
        ),
        5 to Manga(
            id = 5,
            name = "Dragon Ball",
            russian = "Драконий жемчуг",
            image = Image("", "", "", ""),
            url = "",
            score = "8.7",
            genres = listOf(
                Genre(13, "Action", "Экшен", "manga"),
                Genre(14, "Adventure", "Приключения", "manga"),
                Genre(15, "Comedy", "Комедия", "manga")
            ),
            chapters = 519,
            volumes = 42,
            status = "released"
        ),
        6 to Manga(
            id = 6,
            name = "My Hero Academia",
            russian = "Моя геройская академия",
            image = Image("", "", "", ""),
            url = "",
            score = "8.5",
            genres = listOf(
                Genre(16, "Superhero", "Супергерои", "manga"),
                Genre(17, "School", "Школа", "manga"),
                Genre(18, "Action", "Экшен", "manga")
            ),
            chapters = 400,
            volumes = 38,
            status = "ongoing"
        )
    )
    
    override suspend fun getMangaById(mangaId: Int): Flow<Manga?> {
        return flowOf(mockMangas[mangaId])
    }
} 