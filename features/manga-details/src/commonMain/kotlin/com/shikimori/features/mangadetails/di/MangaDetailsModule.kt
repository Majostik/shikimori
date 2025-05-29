package com.shikimori.features.mangadetails.di

import com.shikimori.features.mangadetails.data.repository.MangaDetailsRepositoryImpl
import com.shikimori.features.mangadetails.domain.repository.MangaDetailsRepository
import com.shikimori.features.mangadetails.domain.usecase.GetMangaByIdUseCase
import com.shikimori.features.mangadetails.presentation.MangaDetailsViewModel
import org.koin.dsl.module

val mangaDetailsModule = module {
    // Repository
    single<MangaDetailsRepository> { MangaDetailsRepositoryImpl() }
    
    // Use Case
    factory { GetMangaByIdUseCase(get()) }
    
    // ViewModel
    factory { MangaDetailsViewModel(get()) }
} 