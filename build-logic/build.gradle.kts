plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    maven("https://maven.fabricmc.net/")
}
// version "1.0-SNAPSHOT"
dependencies {
    implementation("fabric-loom:fabric-loom.gradle.plugin:1.0-SNAPSHOT")
}