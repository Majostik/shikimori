plugins {
    alias(libs.plugins.shikimori.android.application)
    alias(libs.plugins.shikimori.compose)
}

android {
    namespace = "com.shikimori"
    
    defaultConfig {
        applicationId = "com.shikimori"
    }
}

dependencies {
    // Navigation module
    implementation(project(":navigation:public"))
    implementation(project(":navigation:impl"))
    
    // Feature modules (needed for DI configuration)
    implementation(project(":features:home"))
    implementation(project(":features:anime"))
    implementation(project(":features:anime-details"))
    implementation(project(":features:manga"))
    implementation(project(":features:manga-details"))
    implementation(project(":features:discovery"))
    implementation(project(":features:settings"))
    
    // Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    
    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    
    // Decompose
    implementation(libs.decompose)
    implementation(libs.decompose.compose)
    
    // Koin
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    
    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    
    // Debug
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
} 