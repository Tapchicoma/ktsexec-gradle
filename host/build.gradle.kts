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
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm-host-embeddable:${Versions.kotlin}")
    implementation("org.jetbrains.kotlin:kotlin-scripting-common:${Versions.kotlin}")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm:${Versions.kotlin}")
}

tasks.register<Jar>("sourcesJar") {
    from(sourceSets.main.get().allSource)
    archiveClassifier.set("sources")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "ktsexec-host"
            from(components["java"])
            artifact(tasks["sourcesJar"])

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

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    setPublications("maven")

    publish = true

    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "maven"
        name = "ktsexec-host"
        vcsUrl = "https://github.com/Tapchicoma/ktsexec-gradle/"
        description = "Provides Kotlin script host to be used with KtsExec Gradle task"
        setLabels("Kotlin", "Gradle", "Kotlin script")
        setLicenses("Apache-2.0")
        desc = description
    })
}
