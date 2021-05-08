import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.python
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

    vcsRoot(Bibik_HttpsGithubComABibikov1987bibikRefsHeadsMain1)

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

    subProject(Bibik)
}

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


object Bibik : Project({
    name = "Bibik"

    vcsRoot(Bibik_HttpsGithubComABibikov1987bibikRefsHeadsMain)

    buildType(Bibik_Test)
    buildType(Bibik_Build)
    buildTypesOrder = arrayListOf(Bibik_Test, Bibik_Build)
})

object Bibik_Build : BuildType({
    name = "Build 1"

    params {
        text("conturs", "12", label = "conturs", display = ParameterDisplay.HIDDEN, allowEmpty = true)
    }

    vcs {
        root(Bibik_HttpsGithubComABibikov1987bibikRefsHeadsMain1)
    }

    steps {
        python {
            name = "step1"
            command = script {
                content = """
                    a = 1+1
                    print ("a")
                    print ("%conturs%")
                """.trimIndent()
            }
        }
        script {
            name = "step2"
            enabled = false
            scriptContent = "echo step2"
        }
        script {
            name = "step3"
            enabled = false
            scriptContent = "echo step3"
        }
        script {
            name = "step4"
            enabled = false
            scriptContent = "echo step4"
        }
    }

    triggers {
        finishBuildTrigger {
            buildType = "${Bibik_Test.id}"
        }
    }
})

object Bibik_Test : BuildType({
    name = "test"
    description = "parallel"

    params {
        select("choose_ver_branch", "", label = "choose version", description = "выберете версию ветки гита", display = ParameterDisplay.PROMPT,
                options = listOf("1.1.4", "1.1.5"))
        select("coose", "", label = "choose", description = "выбрать контур", display = ParameterDisplay.PROMPT,
                options = listOf("1", "2", "3"))
    }

    vcs {
        root(Bibik_HttpsGithubComABibikov1987bibikRefsHeadsMain)

        showDependenciesChanges = true
    }

    steps {
        python {
            name = "step4"
            command = script {
                content = """print ("прошло")"""
            }
        }
    }
})

object Bibik_HttpsGithubComABibikov1987bibikRefsHeadsMain : GitVcsRoot({
    name = "https://github.com/ABibikov1987/bibik#refs/heads/main"
    url = "https://github.com/ABibikov1987/bibik"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "ABibikov1987"
        password = "credentialsJSON:5297c011-7710-4aac-81f0-88e88e00a9bc"
    }
})
