package com.shikimori.navigation.component

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable

interface AppComponent {
    val stack: Value<ChildStack<*, Child>>
    
    // Дочерние компоненты
    sealed class Child {
        data class Home(val component: HomeComponent) : Child()
        data class AnimeDetails(val component: AnimeDetailsComponent) : Child()
        data class MangaDetails(val component: MangaDetailsComponent) : Child()
    }
}

// Конфигурация для навигации
@Serializable
sealed interface Config {
    @Serializable
    data object Home : Config
    
    @Serializable
    data class AnimeDetails(val animeId: Int) : Config
    
    @Serializable
    data class MangaDetails(val mangaId: Int) : Config
} 