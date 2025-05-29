package com.shikimori.navigation.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.shikimori.features.home.presentation.HomeScreen
import com.shikimori.features.animedetails.presentation.AnimeDetailsScreen
import com.shikimori.features.mangadetails.presentation.MangaDetailsScreen
import com.shikimori.navigation.component.AppComponent

@Composable
fun AppContent(component: AppComponent) {
    Children(
        stack = component.stack,
        animation = stackAnimation(fade())
    ) {
        when (val child = it.instance) {
            is AppComponent.Child.Home -> HomeScreen(
                component = child.component
            )
            
            is AppComponent.Child.AnimeDetails -> AnimeDetailsScreen(
                component = child.component
            )
            
            is AppComponent.Child.MangaDetails -> MangaDetailsScreen(
                component = child.component
            )
        }
    }
} 