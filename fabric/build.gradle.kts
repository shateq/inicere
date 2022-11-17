plugins {
    id("inicere.fabric")
}

sourceSets.create("testmod") {
    compileClasspath += sourceSets.main.get().runtimeClasspath
    runtimeClasspath += sourceSets.main.get().runtimeClasspath
}

val testmodImplementation by configurations
dependencies {
    implementation(project(":core"))
    include(project(":core"))

    testmodImplementation(sourceSets.main.get().output)
}

loom {
    runs {
        create("testmodClient") {
            client()
            source(sourceSets.getByName("testmod"))
        }
        create("testmodServer") {
            server()
            source(sourceSets.getByName("testmod"))
        }
    }
}