package Test.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
<<<<<<< HEAD
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger
=======
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
>>>>>>> 16cb27d8891396f99c1c9ae6df047c4ad022e740


version = "2020.2"
project {
    
    buildType(Test_Test)
    buildType(Test_Test2)

        }
<<<<<<< HEAD
=======
    }

    subProject(Test)
    subProject(Finish)
    subProject(Bibik)
}

>>>>>>> 16cb27d8891396f99c1c9ae6df047c4ad022e740

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


<<<<<<< HEAD
<<<<<<< HEAD
object Test_Test2 : BuildType({
    name = "test2"
    description = "bild2"

=======
=======
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


>>>>>>> 944ec5d44725889f924ae6d8129063989d2c15ee
object Test : Project({
    name = "Test"
    description = "тестовый проект"

    buildType(Test_Test)
    buildType(Test_Test2)
})

object Test_Test : BuildType({
    name = "test"

<<<<<<< HEAD
>>>>>>> 16cb27d8891396f99c1c9ae6df047c4ad022e740
    vcs {
        root(DslContext.settingsRoot)
    }

=======
>>>>>>> 944ec5d44725889f924ae6d8129063989d2c15ee
    steps {
        script {
            name = "step1"
<<<<<<< HEAD
            scriptContent = "echo step3"
        }
        script {
=======
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
>>>>>>> 16cb27d8891396f99c1c9ae6df047c4ad022e740
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
