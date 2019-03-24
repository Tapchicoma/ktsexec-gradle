import com.jfrog.bintray.gradle.BintrayExtension

plugins {
    kotlin("jvm")
    `java-library`
    id("org.jlleitschuh.gradle.ktlint")
    `maven-publish`
    id("com.jfrog.bintray")
}

group = "by.egorr.gradle"
version = "1.0.0"

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

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    setPublications("maven")

    publish = true

    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "maven"
        name = "ktsexec-task"
        vcsUrl = "https://github.com/Tapchicoma/ktsexec-gradle/"
        description = "Provides Gradle task to run Kotlin script files"
        setLabels("Kotlin", "Gradle", "Kotlin script")
        setLicenses("Apache-2.0")
        desc = description
    })
}
