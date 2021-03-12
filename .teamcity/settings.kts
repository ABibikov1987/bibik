package Test.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
<<<<<<< HEAD
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger
=======
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
    subProject(Bibik)
}

>>>>>>> 16cb27d8891396f99c1c9ae6df047c4ad022e740

object Test_Test : BuildType({
    name = "test"

    vcs {
        root(DslContext.settingsRoot)
    }

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
object Test_Test2 : BuildType({
    name = "test2"
    description = "bild2"

=======
object Test : Project({
    name = "Test"
    description = "тестовый проект"

    buildType(Test_Test)
    buildType(Test_Test2)
})

object Test_Test : BuildType({
    name = "test"

>>>>>>> 16cb27d8891396f99c1c9ae6df047c4ad022e740
    vcs {
        root(DslContext.settingsRoot)
    }

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

    vcs {
        root(DslContext.settingsRoot)
    }

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
