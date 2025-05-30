package com.shikimori.features.manga.di

import com.shikimori.features.manga.data.remote.MangaApi
import com.shikimori.features.manga.data.repository.MangaRepositoryImpl
import com.shikimori.features.manga.domain.repository.MangaRepository
import com.shikimori.features.manga.domain.usecase.GetMangasUseCase
import com.shikimori.features.manga.domain.usecase.SearchMangasUseCase
import com.shikimori.features.manga.presentation.MangaViewModel
import com.shikimori.network.NetworkModule
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val mangaModule = module {
    single { NetworkModule.provideHttpClient() }
    singleOf(::MangaApi)
    singleOf(::MangaRepositoryImpl) bind MangaRepository::class
    factoryOf(::GetMangasUseCase)
    factoryOf(::SearchMangasUseCase)
    singleOf(::MangaViewModel)
} 