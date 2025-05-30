package com.shikimori

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.defaultComponentContext
import com.shikimori.navigation.component.AppComponentImpl
import com.shikimori.navigation.ui.AppContent
import com.shikimori.designsystem.ShikimoriTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // Configure status bar
        WindowCompat.setDecorFitsSystemWindows(window, false)
        
        // Create root component
        val rootComponent = AppComponentImpl(
            componentContext = defaultComponentContext()
        )
        
        setContent {
            val darkTheme = isSystemInDarkTheme()
            
            // Update status bar icons color based on theme
            LaunchedEffect(darkTheme) {
                WindowCompat.getInsetsController(window, window.decorView).apply {
                    isAppearanceLightStatusBars = !darkTheme
                }
            }
            
            ShikimoriTheme(darkTheme = darkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContent(component = rootComponent)
                }
            }
        }
    }
} 