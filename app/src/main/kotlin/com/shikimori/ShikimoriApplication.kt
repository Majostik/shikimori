package com.shikimori

import android.app.Application
import com.shikimori.features.anime.di.animeModule
import com.shikimori.features.animedetails.di.animeDetailsModule
import com.shikimori.features.discovery.di.discoveryModule
import com.shikimori.features.home.di.homeModule
import com.shikimori.features.manga.di.mangaModule
import com.shikimori.features.mangadetails.di.mangaDetailsModule
import com.shikimori.navigation.di.navigationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ShikimoriApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidLogger()
            androidContext(this@ShikimoriApplication)
            modules(
                navigationModule,
                animeModule,
                animeDetailsModule,
                discoveryModule,
                homeModule,
                mangaModule,
                mangaDetailsModule
            )
        }
    }
} 