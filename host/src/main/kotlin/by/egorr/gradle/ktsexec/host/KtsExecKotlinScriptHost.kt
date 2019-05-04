@file:JvmName("KtsExecKotlinScriptHost")
package by.egorr.gradle.ktsexec.host

import java.io.File
import kotlin.reflect.KClass
import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.EvaluationResult
import kotlin.script.experimental.api.KotlinType
import kotlin.script.experimental.api.ResultWithDiagnostics
import kotlin.script.experimental.host.createCompilationConfigurationFromTemplate
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvm.defaultJvmScriptingHostConfiguration
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.system.exitProcess

@Suppress("Unused")
@KotlinScript
abstract class SimpleScript

internal fun evalFile(
    scriptDefinition: KClass<out Any>,
    scriptFile: File
): ResultWithDiagnostics<EvaluationResult> {
    val compilationConfiguration = createCompilationConfigurationFromTemplate(
        baseClassType = KotlinType(scriptDefinition),
        hostConfiguration = defaultJvmScriptingHostConfiguration
    ) {
        jvm {
            dependenciesFromCurrentContext(wholeClasspath = true)
        }
    }
    return BasicJvmScriptingHost().eval(scriptFile.toScriptSource(), compilationConfiguration, null)
}

fun main(vararg args: String) {
    if (args.isEmpty()) {
        println("usage: <app> <script file>")
        exitProcess(-1)
    }

    println("Host arguments: ${args.joinToString()}")

    val scriptDefinition = Class.forName(args[1].substringAfter('=')).kotlin
    println("Script definition class: $scriptDefinition")

    val scriptFile = File(args[0])
    println("Start script $scriptFile execution.")

    val res = evalFile(scriptDefinition, scriptFile)

    println("Reports: ${res.reports}")
    res.reports.forEach {
        println(" : ${it.message}" + if (it.exception == null) "" else ": ${it.exception} \n ${it.exception?.printStackTrace()}")
    }
    println("Script $scriptFile execution done.")
}
