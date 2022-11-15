plugins {
    id("inicere.parent")
}

version = "1.0.0-SNAPSHOT"
group = "shateq.java"
description = "Config library, not focussed on number of formats"

allprojects {
    project.ext {
        set("mc", "1.18.1")
        set("loader", "0.12.12")
    }
}
