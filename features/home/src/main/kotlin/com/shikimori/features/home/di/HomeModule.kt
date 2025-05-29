package com.shikimori.features.home.di

import com.shikimori.features.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    // ViewModel
    viewModel { HomeViewModel() }
} 