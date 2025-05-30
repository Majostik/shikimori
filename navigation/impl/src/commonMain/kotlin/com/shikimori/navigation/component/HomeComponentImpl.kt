package com.shikimori.navigation.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class HomeComponentImpl(
    componentContext: ComponentContext,
    private val onAnimeClicked: (Int) -> Unit,
    private val onMangaClicked: (Int) -> Unit
) : HomeComponent, ComponentContext by componentContext {
    
    private val _selectedTabIndex = MutableValue(0)
    override val selectedTabIndex: Value<Int> = _selectedTabIndex
    
    override fun selectTab(index: Int) {
        _selectedTabIndex.value = index
    }
    
    override fun onAnimeClicked(animeId: Int) {
        onAnimeClicked.invoke(animeId)
    }
    
    override fun onMangaClicked(mangaId: Int) {
        onMangaClicked.invoke(mangaId)
    }
} 