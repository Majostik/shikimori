plugins {
    alias(libs.plugins.shikimori.android.library)
    alias(libs.plugins.shikimori.compose)
}

android {
    namespace = "com.shikimori.features.home"
}

dependencies {
    // Core modules
    implementation(project(":core"))
    implementation(project(":common"))
    implementation(project(":navigation:public"))
    
    // Feature modules
    implementation(project(":features:anime"))
    implementation(project(":features:manga"))
    implementation(project(":features:discovery"))
    implementation(project(":features:settings"))
    
    // Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    
    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    
    // Navigation
    implementation(libs.androidx.navigation.compose)
    
    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
    
    // Koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    
    // Debug
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
} 