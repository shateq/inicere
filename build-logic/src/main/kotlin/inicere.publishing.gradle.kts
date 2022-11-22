plugins {
    `java-library`
    `maven-publish`
}

group = rootProject.group

publishing {
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            name = "myOrgPrivateRepo"
            url = uri("build/my-repo")
        }
    }
}

tasks.publish {
    dependsOn("check")
}
