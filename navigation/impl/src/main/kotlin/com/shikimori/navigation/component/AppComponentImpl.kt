package com.shikimori.navigation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.DelicateDecomposeApi
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.serializer

@OptIn(DelicateDecomposeApi::class)
class AppComponentImpl(
    componentContext: ComponentContext
) : AppComponent, ComponentContext by componentContext {
    
    private val navigation = StackNavigation<Config>()
    
    override val stack: Value<ChildStack<*, AppComponent.Child>> =
        childStack(
            source = navigation,
            serializer = serializer<Config>(),
            initialConfiguration = Config.Home,
            handleBackButton = true,
            childFactory = ::child
        )
    
    private fun child(config: Config, componentContext: ComponentContext): AppComponent.Child =
        when (config) {
            is Config.Home -> AppComponent.Child.Home(
                component = HomeComponentImpl(
                    componentContext = componentContext,
                    onAnimeClicked = { animeId ->
                        navigation.push(Config.AnimeDetails(animeId))
                    },
                    onMangaClicked = { mangaId ->
                        navigation.push(Config.MangaDetails(mangaId))
                    }
                )
            )
            
            is Config.AnimeDetails -> AppComponent.Child.AnimeDetails(
                component = AnimeDetailsComponentImpl(
                    componentContext = componentContext,
                    animeId = config.animeId,
                    onBackClicked = {
                        navigation.pop()
                    }
                )
            )
            
            is Config.MangaDetails -> AppComponent.Child.MangaDetails(
                component = MangaDetailsComponentImpl(
                    componentContext = componentContext,
                    mangaId = config.mangaId,
                    onBackClicked = {
                        navigation.pop()
                    }
                )
            )
        }
} 