plugins {
    kotlin("jvm") version Versions.kotlin apply false
    id("org.jlleitschuh.gradle.ktlint") version Versions.ktlintGradle apply false
}

allprojects {
    repositories {
        jcenter()
    }
}

tasks.withType(Wrapper::class.java).configureEach {
    gradleVersion = Versions.gradleWrapper
}
