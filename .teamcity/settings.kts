package Test.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script

import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.finishBuildTrigger
import jetbrains.buildServer.configs.kotlin.v2019_2.projectFeatures.buildReportTab
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot



version = "2020.2"
project {
    
    buildType(Test_Test)
    buildType(Test_Test2)

        }

    }

    subProject(Test)
    subProject(Finish)
    subProject(Bibik)
}


object Test_Test : BuildType({
    name = "test"

    steps {
        script {
            name = "step1"
            scriptContent = "echo шаг первый"
        }
        script {
            name = "step2"
            scriptContent = "echo шаг второй"
        }
    }
})


object Test_Test2 : BuildType({
    name = "test2"
    description = "bild2"


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

    vcs {
        root(DslContext.settingsRoot)
    }


    steps {
        script {
            name = "step1"

            scriptContent = "echo step3"
        }
        script {

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
            scriptContent = "echo шаг отработал"
        }
        script {

            name = "step4"
            scriptContent = "echo шаг блок 2 отработал по триггеру и образу"
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
