plugins {
    alias(libs.plugins.shikimori.kmp.library)
    alias(libs.plugins.shikimori.kmp.compose)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(project(":core"))
            
            // Compose
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            
            // Koin
            implementation(libs.koin.core)
        }
        
        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
            api(libs.androidx.lifecycle.viewmodel.ktx)
        }
    }
}

android {
    namespace = "com.shikimori.common"
} 