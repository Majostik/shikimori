plugins {
    alias(libs.plugins.shikimori.kmp.library)
    alias(libs.plugins.shikimori.kmp.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            
            // Feature modules
            
            // Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            
            // Image loading
            implementation(libs.kamel.image)
            
            // ViewModel
            
            // Coroutines
            implementation(libs.kotlinx.coroutines.core)
            
            // Koin
            implementation(libs.koin.core)
        }
        
        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.lifecycle.runtime.ktx)
            implementation(libs.kotlinx.coroutines.android)
        }
    }
}

android {
    namespace = "com.shikimori.designsystem"
} 