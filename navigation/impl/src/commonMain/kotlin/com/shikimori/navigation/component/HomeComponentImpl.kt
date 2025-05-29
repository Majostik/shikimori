package com.shikimori.navigation.component

import com.arkivanov.decompose.ComponentContext

class HomeComponentImpl(
    componentContext: ComponentContext,
    private val onAnimeClicked: (Int) -> Unit,
    private val onMangaClicked: (Int) -> Unit
) : HomeComponent, ComponentContext by componentContext {
    
    override fun onAnimeClicked(animeId: Int) {
        onAnimeClicked.invoke(animeId)
    }
    
    override fun onMangaClicked(mangaId: Int) {
        onMangaClicked.invoke(mangaId)
    }
} 