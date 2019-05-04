includeBuild("../") {
    dependencySubstitution {
        substitute(module("by.egorr.gradle:ktsexec-host")).with(project(":host"))
        substitute(module("by.egorr.gradle:ktsexec-task")).with(project(":task"))
    }
}

include(":custom-script-def")
