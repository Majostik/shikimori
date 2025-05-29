package com.shikimori.features.animedetails.di

import com.shikimori.features.animedetails.data.repository.AnimeDetailsRepositoryImpl
import com.shikimori.features.animedetails.domain.repository.AnimeDetailsRepository
import com.shikimori.features.animedetails.domain.usecase.GetAnimeByIdUseCase
import com.shikimori.features.animedetails.presentation.AnimeDetailsViewModel
import org.koin.dsl.module

val animeDetailsModule = module {
    // Repository
    single<AnimeDetailsRepository> { AnimeDetailsRepositoryImpl() }
    
    // Use Cases
    factory { GetAnimeByIdUseCase(get()) }
    
    // ViewModel
    factory { AnimeDetailsViewModel(get()) }
} 