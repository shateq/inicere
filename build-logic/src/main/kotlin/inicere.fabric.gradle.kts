plugins {
    id("inicere.java-conventions")
    id("fabric-loom")
}

repositories {
    maven("https://maven.terraformersmc.com/releases")
    maven("https://jitpack.io")
}

dependencies {
    minecraft("com.mojang:minecraft:${project.ext["mc"]}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${project.ext["loader"]}")
    //testmodImplementation(sourceSets.main.output)
}

tasks.processResources {
    filteringCharset = "UTF-8"

    filesMatching("fabric.mod.json") {
        expand("version" to rootProject.version)
    }
}
