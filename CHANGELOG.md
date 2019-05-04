# Changelog

### [Unreleased]
  - Update Kotlin to `1.3.31` version
  - Allow to provide custom `@KotlinScript` definitions (#4)

### [1.1.0] - 2019-04-01
  - Add missing sources jar, required by JCenter
  - Change `KtsExec` `script` param type to `RegularFileProperty` (#7)
  - Skip task execution if no `script` param was provided (#8)
  - `KtsExec` task depends only on host dependency (#9)
  - Add no-op Gradle plugin (#3)

### [1.0.0] - 2019-03-24
  - Initial implementation
