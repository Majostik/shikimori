plugins {
    alias(libs.plugins.shikimori.kmp.library)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kermit)
        }
    }
}

android {
    namespace = "com.shikimori.core"
} 