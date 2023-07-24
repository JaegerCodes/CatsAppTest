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
rootProject.name = "Random Cats"
include(":app")
include(":core:testing")
include(":core:network")
include(":core:common")
include(":core:designsystem")
include(":feature-catshome:catshome-data")
include(":feature-catshome:catshome-domain")
include(":feature-catshome:catshome-presentation")
