package by.egorr.gradle.ktsexec

import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.SkipWhenEmpty
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.TaskExecutionException
import java.io.IOException

const val KTS_EXEC_CONFIGURATION_NAME = "ktsExecConfiguration"
private const val HOST_VERSION = "1.1.0"

@Suppress("UnstableApiUsage", "Unused")
open class KtsExec : DefaultTask() {
    /**
     * Script file to execute.
     */
    @get:SkipWhenEmpty
    @get:InputFile
    val script: RegularFileProperty = project.objects.fileProperty()

    /**
     * Full-qualified script definition class name.
     *
     * If you want to provide custom script definition - include it in [KTS_EXEC_CONFIGURATION_NAME] configuration.
     *
     * By default is "by.egorr.gradle.ktsexec.host.SimpleScript".
     */
    @get:Input
    val scriptDefinitionClassName: Property<String> = project.objects.property(String::class.java).apply {
        set("by.egorr.gradle.ktsexec.host.SimpleScript")
    }

    init {
        project.configurations.maybeCreate(KTS_EXEC_CONFIGURATION_NAME)
        with(project.dependencies) {
            add(
                KTS_EXEC_CONFIGURATION_NAME,
                "by.egorr.gradle:ktsexec-host:$HOST_VERSION"
            )
        }
    }

    @TaskAction
    fun runScript() {
        if (script.isPresent) {
            val scriptFile = script.get().asFile
            if (scriptFile.exists()) {
                project.javaexec {
                    it.classpath = project.configurations.getByName(KTS_EXEC_CONFIGURATION_NAME)
                    it.main = "by.egorr.gradle.ktsexec.host.KtsExecKotlinScriptHost"
                    it.args = listOf(scriptFile.absolutePath)
                    it.args("--script-definition=${scriptDefinitionClassName.get()}")
                    it.standardInput = System.`in`
                }
            } else {
                throw TaskExecutionException(this, IOException("Provided script file $scriptFile is not available"))
            }
        } else {
            logger.warn("No script file was provided!")
        }
    }
}
