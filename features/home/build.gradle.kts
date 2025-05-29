plugins {
    alias(libs.plugins.shikimori.kmp.library)
    alias(libs.plugins.shikimori.kmp.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
            implementation(project(":common"))
            implementation(project(":design-system"))
            implementation(project(":navigation:public"))
            
            // Feature modules
            implementation(project(":features:anime"))
            implementation(project(":features:manga"))
            implementation(project(":features:discovery"))
            implementation(project(":features:settings"))
            
            // Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            
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
    namespace = "com.shikimori.features.home"
} 