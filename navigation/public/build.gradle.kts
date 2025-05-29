plugins {
    alias(libs.plugins.shikimori.kmp.library)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(libs.decompose)
        }
    }
}

android {
    namespace = "com.shikimori.navigation.api"
} 