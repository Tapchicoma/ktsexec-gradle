plugins {
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
    `maven-publish`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish")
}

val commonGroup: String by extra
val commonVersion: String by extra
group = commonGroup
version = commonVersion

dependencies {
    compileOnly(gradleApi())
    api(project(":task"))
}

gradlePlugin {
    (plugins) {
        register("ktsExecPlugin") {
            id = "$commonGroup.ktsexec-plugin"
            implementationClass = "by.egorr.gradle.plugin.ktsexec.KtsExecPlugin"
        }
    }
}

pluginBundle {
    vcsUrl = "https://github.com/Tapchicoma/ktsexec-gradle"
    website = vcsUrl
    description = "Provides KtsExec task to run Kotlin scripts"
    tags = listOf("kotlin", "kts", "script", "task")

    (plugins) {
        "ktsExecPlugin" {
            displayName = "KtsExec Gradle plugin"
        }
    }

    mavenCoordinates {
        artifactId = "ktsexec-plugin"
    }
}
