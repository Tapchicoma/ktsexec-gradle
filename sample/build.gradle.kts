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

plugins {
    kotlin("jvm") version "1.3.31" apply false
}

allprojects {
    repositories {
        jcenter()
    }
}

val scriptsGroup = "Scripts"
typealias KtsExec = by.egorr.gradle.ktsexec.KtsExec

tasks.create("hello", KtsExec::class.java) {
    group = scriptsGroup
    script.set(file("scripts/hello.kts"))
}

tasks.create("helloCustomScriptDef", KtsExec::class.java) {
    group = scriptsGroup
    script.set(file("scripts/hello.kts"))
    scriptDefinitionClassName.set("by.egorr.ktsexec.sample.ScriptDefWithReports")
}

dependencies {
    by.egorr.gradle.ktsexec.KTS_EXEC_CONFIGURATION_NAME(project(":custom-script-def"))
}

tasks.withType(Wrapper::class.java).configureEach {
    gradleVersion = "5.4.1"
}
