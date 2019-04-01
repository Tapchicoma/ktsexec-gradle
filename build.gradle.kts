plugins {
    kotlin("jvm") version Versions.kotlin apply false
    id("org.jlleitschuh.gradle.ktlint") version Versions.ktlintGradle apply false
    id("com.jfrog.bintray") version Versions.bintray apply false
    id("com.gradle.plugin-publish") version Versions.gradlePluginPublish apply false
}

allprojects {
    repositories {
        jcenter()
    }

    val commonGroup by extra { "by.egorr.gradle" }
    val commonVersion by extra { "1.2.0-SNAPSHOT" }
}

tasks.withType(Wrapper::class.java).configureEach {
    gradleVersion = Versions.gradleWrapper
}
