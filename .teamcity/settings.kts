import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.buildReportTab
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

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
    subProject(Finish)
    subProject(Bibik)
}


object Bibik : Project({
    name = "Bibik"
})


object Finish : Project({
    name = "finish"

    vcsRoot(Finish_HttpsGithubComABibikov1987bibik)
})

object Finish_HttpsGithubComABibikov1987bibik : GitVcsRoot({
    name = "https://github.com/ABibikov1987/bibik"
    url = "https://github.com/ABibikov1987/bibik"
    branch = "refs/heads/main"
    authMethod = password {
        userName = "ABibikov1987"
        password = "credentialsJSON:70a0a77a-a09c-4fdc-a35d-e20996cb7174"
    }
})


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
