plugins {
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
    id("com.gradle.plugin-publish")
    `java-gradle-plugin`
    `maven-publish`
}

group = "by.egorr.gradle"
version = "1.0.0-SNAPSHOT"

dependencies {
    compileOnly(gradleApi())
    api(kotlin("stdlib"))
}

gradlePlugin {
    (plugins) {
        register("ktsExecPlugin") {
            id = "by.egorr.gradle.ktsexec"
            implementationClass = "by.egorr.gradle.ktsexec.KtsExecPlugin"
        }
    }
}

pluginBundle {
    vcsUrl = "https://github.com/Tapchicoma/ktsexec-gradle"
    website = vcsUrl
    description = "Provides a Gradle task to run kotlin scripts."
    tags = listOf("kotlin", "script", "exec")

    (plugins) {
        "ktsExecPlugin" {
            id = "by.egorr.gradle.ktsexec"
            displayName = "Kotlin script KtsExec task"
        }
    }
}
