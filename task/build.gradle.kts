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
    repositories {
        mavenCentral()
    }

    publications {
        create<MavenPublication>("maven") {
            artifactId = "ktsexec-task"
            from(components["java"])

            pom {
                name.set("KtsExec task")
                description.set("Provides Gradle task (KtsExec) to execute Kotlin scripts")
                url.set("https://github.com/Tapchicoma/ktsexec-gradle/blob/master/README.md")
                licenses {
                    license {
                        name.set("The Apache License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }
                scm {
                    connection.set("scm:git:https://github.com/Tapchicoma/ktsexec-gradle.git")
                    developerConnection.set("scm:git:git@github.com:Tapchicoma/ktsexec-gradle.git")
                    url.set("https://github.com/Tapchicoma/ktsexec-gradle/")
                }
            }
        }
    }
}