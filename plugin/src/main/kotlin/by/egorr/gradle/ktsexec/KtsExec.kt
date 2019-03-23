package by.egorr.gradle.ktsexec

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction

open class KtsExec : DefaultTask() {
    @field:Input
    var script: String? = null

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
