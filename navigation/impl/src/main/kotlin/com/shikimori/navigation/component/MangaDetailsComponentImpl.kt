package com.shikimori.navigation.component

import com.arkivanov.decompose.ComponentContext

class MangaDetailsComponentImpl(
    componentContext: ComponentContext,
    override val mangaId: Int,
    private val onBackClicked: () -> Unit
) : MangaDetailsComponent, ComponentContext by componentContext {
    
    override fun onBackClicked() {
        onBackClicked.invoke()
    }
} 