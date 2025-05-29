package com.shikimori.navigation.component

import com.arkivanov.decompose.ComponentContext

class AnimeDetailsComponentImpl(
    componentContext: ComponentContext,
    override val animeId: Int,
    private val onBackClicked: () -> Unit
) : AnimeDetailsComponent, ComponentContext by componentContext {
    
    override fun onBackClicked() {
        onBackClicked.invoke()
    }
} 