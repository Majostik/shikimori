package com.shikimori.features.discovery.di

import com.shikimori.features.discovery.data.repository.DiscoveryRepositoryImpl
import com.shikimori.features.discovery.domain.repository.DiscoveryRepository
import com.shikimori.features.discovery.domain.usecase.GetTrendingAnimesUseCase
import com.shikimori.features.discovery.presentation.DiscoveryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val discoveryModule = module {
    // Repository
    single<DiscoveryRepository> { DiscoveryRepositoryImpl() }
    
    // Use Cases
    factory { GetTrendingAnimesUseCase(get()) }
    
    // ViewModel
    viewModel { DiscoveryViewModel(get()) }
} 