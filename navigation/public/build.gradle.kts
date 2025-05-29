plugins {
    alias(libs.plugins.shikimori.android.library)
}

android {
    namespace = "com.shikimori.navigation.api"
}

dependencies {
    // Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.navigation.compose)
} 