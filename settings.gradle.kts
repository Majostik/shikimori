pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Shikimori"

include(":app")
include(":core")
include(":common")
include(":navigation:public")
include(":navigation:impl")
include(":features:home")
include(":features:anime")
include(":features:manga")
include(":features:discovery")
include(":features:settings")
include(":features:anime-details")
include(":features:manga-details") 