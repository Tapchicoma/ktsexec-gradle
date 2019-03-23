package by.egorr.gradle.ktsexec

import org.gradle.api.Plugin
import org.gradle.api.Project

const val KTS_EXEC_CONFIGURATION_NAME = "ktsExecConfiguration"

class KtsExecPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.configurations.create(KTS_EXEC_CONFIGURATION_NAME)
        with(target.dependencies) {
            add(
                KTS_EXEC_CONFIGURATION_NAME,
                "org.jetbrains.kotlin:kotlin-scripting-jvm-host-embeddable:1.3.21"
            )
            add(
                KTS_EXEC_CONFIGURATION_NAME,
                "org.jetbrains.kotlin:kotlin-scripting-jvm-host-embeddable:1.3.21"
            )
            add(
                KTS_EXEC_CONFIGURATION_NAME,
                "org.jetbrains.kotlin:kotlin-scripting-common:1.3.21"
            )
            add(
                KTS_EXEC_CONFIGURATION_NAME,
                "org.jetbrains.kotlin:kotlin-scripting-jvm:1.3.21"
            )
            add(
                KTS_EXEC_CONFIGURATION_NAME,
                "org.jetbrains.kotlin:kotlin-compiler-embeddable:1.3.21"
            )
            add(
                KTS_EXEC_CONFIGURATION_NAME,
                "org.jetbrains.kotlin:kotlin-reflect:1.3.21"
            )
            add(
                KTS_EXEC_CONFIGURATION_NAME,
                "by.egorr.gradle:ktsexec-host:1.0.0-SNAPSHOT"
            )
        }
    }
}
