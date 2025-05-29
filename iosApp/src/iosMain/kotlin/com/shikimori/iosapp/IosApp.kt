package com.shikimori.iosapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ComposeUIViewController
import com.shikimori.designsystem.ShikimoriTheme
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.shikimori.navigation.component.AppComponent
import com.shikimori.navigation.component.AppComponentImpl
import com.shikimori.navigation.ui.AppContent
import com.shikimori.features.anime.di.animeModule
import com.shikimori.features.animedetails.di.animeDetailsModule
import com.shikimori.features.discovery.di.discoveryModule
import com.shikimori.features.home.di.homeModule
import com.shikimori.features.manga.di.mangaModule
import com.shikimori.features.mangadetails.di.mangaDetailsModule
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    // Initialize Koin
    startKoin {
        modules(
            animeModule,
            animeDetailsModule,
            discoveryModule,
            homeModule,
            mangaModule,
            mangaDetailsModule
        )
    }
    
    // Create app component
    val lifecycle = LifecycleRegistry()
    val appComponent = AppComponentImpl(
        componentContext = DefaultComponentContext(lifecycle = lifecycle)
    )
    
    return ComposeUIViewController {
        App(appComponent)
    }
}

@Composable
private fun App(appComponent: AppComponent) {
    ShikimoriTheme {
        AppContent(component = appComponent)
    }
}
