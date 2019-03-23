plugins {
    kotlin("jvm") version "1.3.21" apply false
    id("org.jlleitschuh.gradle.ktlint") version "7.2.1" apply false
    id("com.gradle.plugin-publish") version "0.10.0" apply false
}

allprojects {
    repositories {
        jcenter()
    }
}

tasks.withType(Wrapper::class.java).configureEach {
    gradleVersion = "5.3"
}
