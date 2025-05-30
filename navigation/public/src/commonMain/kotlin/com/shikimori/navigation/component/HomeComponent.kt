package com.shikimori.navigation.component

import com.arkivanov.decompose.value.Value

interface HomeComponent {
    val selectedTabIndex: Value<Int>
    
    fun selectTab(index: Int)
    fun onAnimeClicked(animeId: Int)
    fun onMangaClicked(mangaId: Int)
} 