package Test.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger


version = "2020.2"
project {
    
    buildType(Test_Test)
    buildType(Test_Test2)

        }

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
