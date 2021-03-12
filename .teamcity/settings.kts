import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.buildReportTab

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

    subProject(Bibik)
}


object Bibik : Project({
    name = "Bibik"

    buildType(Bibik_Build)
})

object Bibik_Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        script {
            name = "test step"
            scriptContent = "echo its worck"
        }
        script {
            name = "step2"
            scriptContent = "echo step2"
        }
    }
})
<<<<<<< HEAD


object Testtest : BuildType({
    name = "testtest"

    artifactRules = "%teamcity.build.step.name%"

    steps {
        script {
        name = "1 шаг первый "
        scriptContent = """
            echo is done
            """.trimident()
        formatStderrAsError

        }
    }
})

object Publish : BuildType({
    name="build2"

    steps {
        script {

        name = "2 проверка результатов первого шага"
        scriptContent = """
            echo is done
            """.trimident()
        formatStderrAsError
    
       }
   }

   dependencies {
   artifacts(RelativeId("Testtest")) {
        buildRule = tag ("testtest")
        artifactRules = "%teamcity.build.step.name%"
       }
   }
})


=======
>>>>>>> b12320a5923555724e17a234736ea68b6d73bea1
