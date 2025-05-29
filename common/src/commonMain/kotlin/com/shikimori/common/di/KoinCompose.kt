package com.shikimori.common.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object KoinHelper : KoinComponent

@Composable
inline fun <reified T : Any> koinInject(): T {
    return remember { KoinHelper.inject<T>().value }
} 