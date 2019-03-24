# KtsExec Gradle task

Project provides `KtsExec` Gradle task that runs [Kotlin scripts](https://github.com/Kotlin/KEEP/blob/scripting/proposals/scripting-support.md).

[![CircleCI](https://circleci.com/gh/Tapchicoma/ktsexec-gradle.svg?style=svg)](https://circleci.com/gh/Tapchicoma/ktsexec-gradle)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

## Gradle usage

Add following block to your root `build.gradle.kts`:
```kotlin
buildscript {
    dependencies {
        classpath("by.egorr.gradle:ktsexec-task:1.0.0")
    }
}

repositories {
    // Required for KtsExec task dependencies
    jcenter()
}
```
**Note**: this project provides `KtsExec` task as a common dependency, 
and not as Gradle plugin.

Then start adding tasks, similar to `JavaExec`:
```kotlin
tasks.create("runSomScript", by.egorr.gradle.ktsexec.KtsExec::class.java) {
    group = "Scripts"
    script = "scripts/awesome_script.kts"
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
