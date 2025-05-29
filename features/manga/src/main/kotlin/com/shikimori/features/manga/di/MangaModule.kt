package com.shikimori.features.manga.di

import com.shikimori.features.manga.data.repository.MangaRepositoryImpl
import com.shikimori.features.manga.domain.repository.MangaRepository
import com.shikimori.features.manga.domain.usecase.GetMangasUseCase
import com.shikimori.features.manga.domain.usecase.SearchMangasUseCase
import com.shikimori.features.manga.presentation.MangaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mangaModule = module {
    // Repository
    single<MangaRepository> { MangaRepositoryImpl() }
    
    // Use Cases
    factory { GetMangasUseCase(get()) }
    factory { SearchMangasUseCase(get()) }
    
    // ViewModel
    viewModel { MangaViewModel(get(), get()) }
} 