plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    maven("https://maven.fabricmc.net/")
}

dependencies {
    implementation("fabric-loom:fabric-loom.gradle.plugin:1.0-SNAPSHOT")
}