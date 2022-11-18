plugins {
    id("inicere.test-java")
    id("inicere.publishing")
}

java {
    withSourcesJar()
}

dependencies {
    implementation("org.tomlj:tomlj:1.1.0")
    //implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-toml:2.14.0")
}
