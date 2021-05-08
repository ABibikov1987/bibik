import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.python
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.buildReportTab
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
    defaultTemplate = RelativeId("PiplinePython")

    vcsRoot(Bibik_HttpsGithubComABibikov1987bibikRefsHeadsMain1)

    template(PiplinePython)

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
}

object PiplinePython : Template({
    name = "pipline_Python"
    description = "pipline_Python"

    params {
        select("a", "", label = "a", description = "a", display = ParameterDisplay.PROMPT,
                options = listOf("3", "4", "5"))
        select("b", "", label = "b", description = "b", display = ParameterDisplay.PROMPT,
                options = listOf("7", "6", "5"))
    }

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            id = "RUNNER_6"
            scriptContent = "echo copy file is done"
        }
        python {
            id = "RUNNER_7"
            command = script {
                content = "print (%a%+%b%)"
            }
        }
    }
})

object Bibik_HttpsGithubComABibikov1987bibikRefsHeadsMain1 : GitVcsRoot({
    name = "https://github.com/ABibikov1987/bibik#refs/heads/main (1)"
    url = "https://github.com/ABibikov1987/bibik"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "ABibikov1987"
        password = "credentialsJSON:5297c011-7710-4aac-81f0-88e88e00a9bc"
    }
})
