plugins {
    alias(libs.plugins.shikimori.android.library)
    alias(libs.plugins.shikimori.compose)
}

android {
    namespace = "com.shikimori.navigation.impl"
}

dependencies {
    // Navigation public API
    implementation(project(":navigation:public"))
    
    // Feature modules
    implementation(project(":features:home"))
    implementation(project(":features:anime"))
    implementation(project(":features:manga"))
    implementation(project(":features:discovery"))
    implementation(project(":features:settings"))
    implementation(project(":features:anime-details"))
    implementation(project(":features:manga-details"))
    
    // Core Android
    implementation(libs.androidx.core.ktx)
    
    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
    
    // Navigation
    implementation(libs.androidx.navigation.compose)
    
    // Koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
} 