pluginManagement {
    repositories {
        maven { url "https://maven.fabricmc.net/" }
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "Inicere"

include(":core", ":fabric")
