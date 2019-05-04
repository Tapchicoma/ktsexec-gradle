@file:JvmName("KtsExecKotlinScriptHost")
package by.egorr.gradle.ktsexec.host

import java.io.File
import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.EvaluationResult
import kotlin.script.experimental.api.ResultWithDiagnostics
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvm.dependenciesFromCurrentContext
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.script.experimental.jvmhost.createJvmCompilationConfigurationFromTemplate
import kotlin.system.exitProcess

@KotlinScript
abstract class SimpleScript

inline fun <reified S : Any> evalFile(
    scriptFile: File
): ResultWithDiagnostics<EvaluationResult> {
    val compilationConfiguration = createJvmCompilationConfigurationFromTemplate<S> {
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

    val scriptFile = File(args[0])
    println("Start script $scriptFile execution.")

    val res = evalFile<SimpleScript>(scriptFile)

    res.reports.forEach {
        println(" : ${it.message}" + if (it.exception == null) "" else ": ${it.exception} \n ${it.exception?.printStackTrace()}")
    }
    println("Script $scriptFile execution done.")
}
