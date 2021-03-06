# KtsExec Gradle task

Project provides `KtsExec` Gradle task that runs [Kotlin scripts](https://github.com/Kotlin/KEEP/blob/scripting/proposals/scripting-support.md).

[![CircleCI](https://circleci.com/gh/Tapchicoma/ktsexec-gradle.svg?style=svg)](https://circleci.com/gh/Tapchicoma/ktsexec-gradle)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
[![Download](https://api.bintray.com/packages/tapchicoma/maven/ktsexec-task/images/download.svg)](https://bintray.com/tapchicoma/maven/ktsexec-task/_latestVersion)

## Gradle setup

### No-op plugin

You can add no-op plugin, that automatically adds `KtsExec` task type
and does nothing more:
```kotlin
plugins {
    id("by.egorr.gradle.ktsexec-plugin") version "1.1.0"
}

repositories {
    // Required for KtsExec task dependencies
    jcenter()
}
```

### Adding task directly

Alternatively, you can add following block to your root `build.gradle.kts`
to get `KtsExec` task type:
```kotlin
buildscript {
    repositories {
        jcenter()
    }

    dependencies {
        classpath("by.egorr.gradle:ktsexec-task:1.1.0")
    }
}

repositories {
    // Required for KtsExec task dependencies
    jcenter()
}
```

## Usage

Start adding tasks, similar to `JavaExec`:
```kotlin
tasks.create("runSomeScript", by.egorr.gradle.ktsexec.KtsExec::class.java) {
    group = "Scripts"
    script.set(file("scripts/awesome_script.kts"))
}
```

Check also [`sample/`](sample/) directory that provides sample Gradle project.

## Script limitations

Current version supports only simple Kotlin scripts, 
without any advanced use-cases (like external dependencies).

## Useful links

- https://github.com/NikkyAI/MinimalScriptingExample - implementation inspiration repo
- [Kotlin script KEEP](https://github.com/Kotlin/KEEP/blob/scripting/proposals/scripting-support.md)
- [Official script examples](https://github.com/JetBrains/kotlin/tree/master/libraries/examples/scripting)
