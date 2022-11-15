plugins {
    id("inicere.fabric")
}

dependencies {
    implementation(project(":core"))
    include(project(":core"))
}

/* TODO: testmod to kotlin dsl
sourceSets {
    testmod {
        compileClasspath += sourceSets.main.compileClasspath
        runtimeClasspath += sourceSets.main.runtimeClasspath
    }
}
loom {
    runs {
        testmodClient {
            client()
            source(sourceSets.testmod)
        }

        testmodServer {
            server()
            source(sourceSets.testmod)
        }
    }
}*/