package com.shikimori.features.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shikimori.features.home.presentation.HomeScreen
import com.shikimori.navigation.Navigator
import com.shikimori.navigation.Routes

const val HOME_ROUTE = Routes.HOME

fun NavGraphBuilder.homeScreen(navigator: Navigator) {
    composable(HOME_ROUTE) {
        HomeScreen(navigator = navigator)
    }
} 