plugins {
    `kotlin-dsl`
}
repositories {
    gradlePluginPortal()
    maven("https://maven.fabricmc.net/")
    maven("https://repo.jpenilla.xyz/snapshots/")
}
dependencies {
    implementation("fabric-loom", "fabric-loom.gradle.plugin", "1.0-SNAPSHOT") //version "1.0-SNAPSHOT"
    implementation("xyz.jpenilla.run-paper",  "xyz.jpenilla.run-paper.gradle.plugin", "2.0.1-SNAPSHOT")
    implementation("net.minecrell.plugin-yml.bukkit", "net.minecrell.plugin-yml.bukkit.gradle.plugin", "0.5.2")
}
