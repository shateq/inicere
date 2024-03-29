import java.time.LocalDate

plugins {
    java
}

base.archivesName.set("inicere-${project.name}-${version}")
version = rootProject.version
group = rootProject.group

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://maven.terraformersmc.com/releases")
    maven("https://jitpack.io")
}

dependencies.implementation("org.jetbrains", "annotations", project.ext["jb"] as String)

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
        options.release.set(17)
    }
    javadoc {
        options.encoding = "UTF-8"
        //options.destinationDirectory(File(rootDir, "docs"))
    }
    jar {
        from(File(rootDir, "LICENSE")) {
            rename { "LICENSE_${rootProject.name}" }
        }
        manifest {
            attributes(
                "Implementation-Title" to "inicere-"+project.name,
                "Implementation-Version" to rootProject.version,
                "Implementation-Build-Date" to LocalDate.now(),
                "Package" to "shateq.inicere"
            )
        }
    }
}
