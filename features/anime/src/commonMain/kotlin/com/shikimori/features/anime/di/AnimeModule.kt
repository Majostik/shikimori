package com.shikimori.features.anime.di

import com.shikimori.features.anime.data.remote.AnimeApi
import com.shikimori.features.anime.data.repository.AnimeRepositoryImpl
import com.shikimori.features.anime.domain.repository.AnimeRepository
import com.shikimori.features.anime.domain.usecase.GetAnimesUseCase
import com.shikimori.features.anime.domain.usecase.SearchAnimesUseCase
import com.shikimori.features.anime.presentation.AnimeViewModel
import com.shikimori.network.NetworkModule
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val animeModule = module {
    
    single { NetworkModule.provideHttpClient() }
    
    singleOf(::AnimeApi)
    singleOf(::AnimeRepositoryImpl) bind AnimeRepository::class
    factoryOf(::GetAnimesUseCase)
    factoryOf(::SearchAnimesUseCase)
    factoryOf(::AnimeViewModel)
} 