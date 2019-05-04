buildscript {
    repositories {
        jcenter()
    }
    
    dependencies {
        classpath("by.egorr.gradle:ktsexec-task:1.0.0-SNAPSHOT")
        // Needed only in this sample project!
        classpath("by.egorr.gradle:ktsexec-host:1.0.0-SNAPSHOT")
    }
}

repositories {
    jcenter()
}

tasks.create("hello", by.egorr.gradle.ktsexec.KtsExec::class.java) {
    group = "Scripts"
    script.set(file("scripts/hello.kts"))
}

tasks.withType(Wrapper::class.java).configureEach {
    gradleVersion = "5.4.1"
}
