package com.shikimori.features.mangadetails.di

import com.shikimori.features.mangadetails.data.api.MangaDetailsApi
import com.shikimori.features.mangadetails.data.repository.MangaDetailsRepositoryImpl
import com.shikimori.features.mangadetails.domain.repository.MangaDetailsRepository
import com.shikimori.features.mangadetails.domain.usecase.GetMangaByIdUseCase
import com.shikimori.features.mangadetails.presentation.MangaDetailsViewModel
import com.shikimori.network.NetworkModule
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mangaDetailsModule = module {
    // API
    single { MangaDetailsApi(NetworkModule.provideHttpClient()) }
    
    // Repository
    single<MangaDetailsRepository> { MangaDetailsRepositoryImpl(get()) }
    
    // Use Case
    factory { GetMangaByIdUseCase(get()) }
    
    // ViewModel
    singleOf(::MangaDetailsViewModel)
} 