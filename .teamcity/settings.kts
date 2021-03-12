import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.buildReportTab
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2020.2"

project {
    description = "Contains all other projects"

    features {
        buildReportTab {
            id = "PROJECT_EXT_1"
            title = "Code Coverage"
            startPage = "coverage.zip!index.html"
        }
    }

    cleanup {
        baseRule {
            preventDependencyCleanup = false
        }
    }

    subProject(Test)
}


object Test : Project({
    name = "Test"
    description = "тестовый проект"

    buildType(Test_Test)
    buildType(Test_Test2)
})

object Test_Test : BuildType({
    name = "test"

    steps {
        script {
            name = "step1"
            scriptContent = "echo step1"
        }
        script {
            name = "step2"
            scriptContent = "echo step2"
        }
    }
})

object Test_Test2 : BuildType({
    name = "test2"
    description = "bild2"

    steps {
        script {
            name = "step1"
            scriptContent = "echo step3"
        }
        script {
            name = "step4"
            scriptContent = "echo step4"
        }
    }

    triggers {
        finishBuildTrigger {
            buildType = "${Test_Test.id}"
            successfulOnly = true
        }
    }

    dependencies {
        snapshot(Test_Test) {
            onDependencyFailure = FailureAction.FAIL_TO_START
        }
    }
})
