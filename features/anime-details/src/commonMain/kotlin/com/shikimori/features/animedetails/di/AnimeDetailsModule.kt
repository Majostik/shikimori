package com.shikimori.features.animedetails.di

import com.shikimori.features.animedetails.data.api.AnimeDetailsApi
import com.shikimori.features.animedetails.data.repository.AnimeDetailsRepositoryImpl
import com.shikimori.features.animedetails.domain.repository.AnimeDetailsRepository
import com.shikimori.features.animedetails.domain.usecase.GetAnimeByIdUseCase
import com.shikimori.features.animedetails.presentation.AnimeDetailsViewModel
import com.shikimori.network.NetworkModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val animeDetailsModule = module {
    // API
    single { AnimeDetailsApi(NetworkModule.provideHttpClient()) }
    
    // Repository
    single<AnimeDetailsRepository> { AnimeDetailsRepositoryImpl(get()) }
    
    // Use Cases
    factory { GetAnimeByIdUseCase(get()) }
    
    // ViewModel
    singleOf(::AnimeDetailsViewModel)
} 