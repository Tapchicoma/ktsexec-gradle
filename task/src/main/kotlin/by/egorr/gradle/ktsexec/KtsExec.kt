package by.egorr.gradle.ktsexec

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

const val KTS_EXEC_CONFIGURATION_NAME = "ktsExecConfiguration"
private const val KOTLIN_VERSION = "1.3.21"

open class KtsExec : DefaultTask() {
    @field:Input
    var script: String? = null

    init {
        project.configurations.maybeCreate(KTS_EXEC_CONFIGURATION_NAME)
        with(project.dependencies) {
            add(
                KTS_EXEC_CONFIGURATION_NAME,
                "org.jetbrains.kotlin:kotlin-scripting-jvm-host-embeddable:$KOTLIN_VERSION"
            )
            add(
                KTS_EXEC_CONFIGURATION_NAME,
                "org.jetbrains.kotlin:kotlin-scripting-jvm:$KOTLIN_VERSION"
            )
            add(
                KTS_EXEC_CONFIGURATION_NAME,
                "org.jetbrains.kotlin:kotlin-compiler-embeddable:$KOTLIN_VERSION"
            )
            add(
                KTS_EXEC_CONFIGURATION_NAME,
                "by.egorr.gradle:ktsexec-host:1.0.0-SNAPSHOT"
            )
        }
    }

    @TaskAction
    fun runScript() {
        val scriptFile = script
        if (scriptFile != null) {
            project.javaexec {
                it.classpath = project.configurations.getByName(KTS_EXEC_CONFIGURATION_NAME)
                it.main = "by.egorr.gradle.ktsexec.host.KtsExecKotlinScriptHost"
                it.args = listOf(scriptFile)
                it.standardInput = System.`in`
            }
        } else {
            logger.warn("No script file was provided!")
        }
    }
}
