plugins {
    id("by.egorr.gradle.ktsexec") version "1.0.0-SNAPSHOT"
}

repositories {
    jcenter()
}

tasks.create("hello", by.egorr.gradle.ktsexec.KtsExec::class.java) {
    group = "Scripts"
    script = "scripts/hello.kts"
}
