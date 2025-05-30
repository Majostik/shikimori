package com.shikimori.features.home.di

import com.shikimori.features.home.presentation.HomeViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val homeModule = module {
    // ViewModel
    singleOf(::HomeViewModel)
} 