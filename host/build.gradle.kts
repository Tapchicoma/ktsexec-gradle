plugins {
    kotlin("jvm")
    `java-library`
    id("org.jlleitschuh.gradle.ktlint")
    `maven-publish`
}

group = "by.egorr.gradle"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm-host-embeddable:1.3.21")
    implementation("org.jetbrains.kotlin:kotlin-scripting-common:1.3.21")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm:1.3.21")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "ktsexec-host"
            from(components["java"])
        }
    }
}
