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

            pom {
                name.set("Kotlin script jvm host")
                description.set("Provides Kotlin script jvm host that is used by KtsExec Gradle task")
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
