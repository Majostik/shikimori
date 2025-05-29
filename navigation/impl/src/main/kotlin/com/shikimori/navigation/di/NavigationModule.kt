package com.shikimori.navigation.di

import androidx.navigation.NavController
import com.shikimori.navigation.Navigator
import com.shikimori.navigation.NavigatorImpl
import org.koin.dsl.module

val navigationModule = module {
    factory<Navigator> { (navController: NavController) ->
        NavigatorImpl(navController)
    }
} 