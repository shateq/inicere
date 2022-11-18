plugins {
    id("inicere.parent")
}

version = "1.0.0-SNAPSHOT"
group = "shateq.java"
description = "Config library, not focussed on number of formats"

allprojects {
    project.ext {
        set("mc", "1.18.2")
        set("loader", "0.14.10")
        set("fapi", "0.59.0+1.18.2")
        set("modmenu", "3.2.3")
    }
}
