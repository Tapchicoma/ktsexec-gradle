plugins {
    kotlin("jvm")
    `java-library`
    id("org.jlleitschuh.gradle.ktlint")
    `maven-publish`
}

group = "by.egorr.gradle"
version = "1.0.0-SNAPSHOT"

dependencies {
    compileOnly(gradleApi())
    compileOnly(kotlin("stdlib"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "ktsexec-task"
            from(components["java"])
        }
    }
}