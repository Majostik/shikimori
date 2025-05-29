package com.shikimori.features.anime.di

import com.shikimori.features.anime.data.repository.AnimeRepositoryImpl
import com.shikimori.features.anime.domain.repository.AnimeRepository
import com.shikimori.features.anime.domain.usecase.GetAnimesUseCase
import com.shikimori.features.anime.domain.usecase.SearchAnimesUseCase
import com.shikimori.features.anime.presentation.AnimeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val animeModule = module {
    // Repository
    single<AnimeRepository> { AnimeRepositoryImpl() }
    
    // Use Cases
    factory { GetAnimesUseCase(get()) }
    factory { SearchAnimesUseCase(get()) }
    
    // ViewModel
    viewModel { AnimeViewModel(get(), get()) }
} 