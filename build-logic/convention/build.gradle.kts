plugins {
    `kotlin-dsl`
}

group = "com.shikimori.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    implementation("org.jetbrains.compose:compose-gradle-plugin:1.7.0")
}

gradlePlugin {
    plugins {
        register("kmpLibrary") {
            id = "shikimori.kmp.library"
            implementationClass = "KmpLibraryConventionPlugin"
        }
        register("kmpCompose") {
            id = "shikimori.kmp.compose"
            implementationClass = "KmpComposeConventionPlugin"
        }
    }
} 