pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://repo.jpenilla.xyz/snapshots/")
    }
}
includeBuild("build-logic")

rootProject.name = "inicere"
include(":core", ":fabric")
