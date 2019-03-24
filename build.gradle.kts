plugins {
    kotlin("jvm") version Versions.kotlin apply false
    id("org.jlleitschuh.gradle.ktlint") version Versions.ktlintGradle apply false
    id("com.jfrog.bintray") version Versions.bintray
}

allprojects {
    repositories {
        jcenter()
    }
}

tasks.withType(Wrapper::class.java).configureEach {
    gradleVersion = Versions.gradleWrapper
}
