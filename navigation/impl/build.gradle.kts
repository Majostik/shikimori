plugins {
    alias(libs.plugins.shikimori.kmp.library)
    alias(libs.plugins.shikimori.kmp.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":navigation:public"))
            implementation(project(":common"))
            
            // Feature modules
            implementation(project(":features:home"))
            implementation(project(":features:anime-details"))
            implementation(project(":features:manga-details"))
            
            // Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            
            // Decompose
            implementation(libs.decompose.compose)
            
            // Koin
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.shikimori.navigation.impl"
} 