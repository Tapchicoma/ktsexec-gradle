package by.egorr.gradle.plugin.ktsexec

import by.egorr.gradle.ktsexec.KTS_EXEC_CONFIGURATION_NAME
import org.gradle.api.Plugin
import org.gradle.api.Project

open class KtsExecPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.configurations.maybeCreate(KTS_EXEC_CONFIGURATION_NAME)
    }
}
