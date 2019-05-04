package by.egorr.ktsexec.sample

import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.ScriptCompilationConfiguration
import kotlin.script.experimental.api.ScriptDiagnostic
import kotlin.script.experimental.api.asSuccess
import kotlin.script.experimental.api.refineConfiguration

@KotlinScript(
    compilationConfiguration = ReportsCompilationConfiguration::class
)
abstract class ScriptDefWithReports

class ReportsCompilationConfiguration : ScriptCompilationConfiguration({
    refineConfiguration {
        beforeParsing {
            val diagnostic = ScriptDiagnostic(
                "beforeParsing time: ${System.currentTimeMillis()}",
                ScriptDiagnostic.Severity.INFO
            )
            it.compilationConfiguration.asSuccess(listOf(diagnostic))
        }

        beforeCompiling {
            val diagnostic = ScriptDiagnostic(
                "beforeCompiling time: ${System.currentTimeMillis()}",
                ScriptDiagnostic.Severity.INFO
            )
            it.compilationConfiguration.asSuccess(listOf(diagnostic))
        }
    }
})
