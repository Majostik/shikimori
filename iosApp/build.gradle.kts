plugins {
    alias(libs.plugins.shikimori.kmp.library)
    alias(libs.plugins.shikimori.kmp.compose)
}

kotlin {
    iosArm64()
    iosX64()
    iosSimulatorArm64()
    
    sourceSets {
        iosMain.dependencies {
            // Navigation module
            implementation(project(":navigation:public"))
            implementation(project(":navigation:impl"))
            
            // Feature modules
            implementation(project(":features:home"))
            implementation(project(":features:anime"))
            implementation(project(":features:anime-details"))
            implementation(project(":features:manga"))
            implementation(project(":features:manga-details"))
            implementation(project(":features:discovery"))
            implementation(project(":features:settings"))
            
            // Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            
            // Decompose
            implementation(libs.decompose)
            implementation(libs.decompose.compose)
            
            // Koin
            implementation(libs.koin.core)
        }
    }
}

android {
    namespace = "com.shikimori.iosapp"
}
