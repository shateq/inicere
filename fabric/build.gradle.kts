plugins {
    id("inicere.fabric")
}

dependencies {
    implementation(project(":core"))
    include(project(":core"))
}

sourceSets.create("testmod") {
    compileClasspath += sourceSets.main.get().compileClasspath
    runtimeClasspath += sourceSets.main.get().runtimeClasspath
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