plugins {
    alias(libs.plugins.shikimori.android.library)
    alias(libs.plugins.shikimori.compose)
}

android {
    namespace = "com.shikimori.common"
}

dependencies {
    // Core modules
    implementation(project(":core"))
    
    // Core Android
    implementation(libs.androidx.core.ktx)
    
    // Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    
    // Coil
    implementation(libs.coil.compose)
    
    // Debug
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
} 