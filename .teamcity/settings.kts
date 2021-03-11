import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
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

    vcsRoot(Bibik_HttpsABibikov1987githubComABibikov1987bibikRefsHeadsMain)

    buildType(Bibik_Build)
})

object Bibik_Build : BuildType({
    name = "Build"

    vcs {
        root(Bibik_HttpsABibikov1987githubComABibikov1987bibikRefsHeadsMain)
    }

})

object Bibik_HttpsABibikov1987githubComABibikov1987bibikRefsHeadsMain : GitVcsRoot({
    name = "https://ABibikov1987@github.com/ABibikov1987/bibik#refs/heads/main"
    url = "https://ABibikov1987@github.com/ABibikov1987/bibik"
    branch = "refs/heads/main"
    branchSpec = "refs/heads/*"
    authMethod = password {
        userName = "ABibikov1987"
        password = "credentialsJSON:5297c011-7710-4aac-81f0-88e88e00a9bc"
    }
})


object Package : BuildType({
   name = "build1"

   artifactRules = "%teamcity.build.step.name%"

   steps {
       script {

       echo "1 шаг первый "
       scriptContent = """
       
       """.trimident()
    formatStderrAsError

       }

   }
})

object Publish : BuildType({
   name="build2"

   steps {
       script {

       echo "2 проверка результатов первого шага"
       
       """.trimident()
    formatStderrAsError
    
       }
   }

   dependencies {
   artifacts(RelativeId("testtest")) {
        buildRule = tag ("testtest")
        artifactRules = "%teamcity.build.step.name%"
       }
   }
})


