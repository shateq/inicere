plugins {
    id("inicere.java-conventions")
    id("fabric-loom")
}

repositories {
    maven("https://maven.terraformersmc.com/releases")
    maven("https://jitpack.io")
}

dependencies {
    minecraft("com.mojang", "minecraft", project.ext["mc"] as String)
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc", "fabric-loader", project.ext["loader"] as String)
    modImplementation("net.fabricmc.fabric-api", "fabric-api", project.ext["fapi"] as String)
}

tasks.processResources {
    filteringCharset = "UTF-8"
    filesMatching("fabric.mod.json") {
        expand("version" to rootProject.version)
    }
}
