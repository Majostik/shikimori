plugins {
    alias(libs.plugins.shikimori.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.shikimori.core"
}

dependencies {
    // Core Android
    implementation(libs.androidx.core.ktx)
    
    // Serialization
    implementation(libs.kotlinx.serialization.json)
} 