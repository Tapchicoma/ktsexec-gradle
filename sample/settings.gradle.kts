includeBuild("../") {
    dependencySubstitution {
        substitute(module("by.egorr.gradle:ktsexec-host")).with(project(":host"))
        substitute(module("by.egorr.gradle:plugin")).with(project(":plugin"))
    }
    pluginManagement {
        resolutionStrategy {
            eachPlugin {
                when (requested.id.id) {
                    "by.egorr.gradle.ktsexec" -> useModule("by.egorr.gradle:plugin:1.0.0-SNAPSHOT")
                }
            }
        }
    }
}
