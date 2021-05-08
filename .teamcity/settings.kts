import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.python
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

    subProject(Python)
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


object Python : Project({
    name = "python"
    description = "Python"

    buildType(Python_PythonPipline)
})

object Python_PythonPipline : BuildType({
    name = "python_pipline"

    params {
        select("namespase", "", label = "namespase", display = ParameterDisplay.PROMPT,
                options = listOf(""""a"""", """"b""""))
        select("stend", "", label = "stend", display = ParameterDisplay.PROMPT,
                options = listOf(""""K3"""", """"K4"""", """"NT"""", """"IFT"""", """"PSI"""", """"PROD""""))
        select("kontur", "", label = "kontur", display = ParameterDisplay.PROMPT,
                options = listOf(""""rb"""", """"kb"""", """"oc""""))
        text("path_to_cfg", """C:\test\K4.cfg""", label = "path_to_cfg", display = ParameterDisplay.HIDDEN, allowEmpty = false)
    }

    steps {
        python {
            pythonVersion = customPython {
                executable = """C:\Users\admin\AppData\Local\Programs\Python\Python36\python.exe"""
            }
            command = script {
                content = """
                    #slovar = {'K4': {'rb': 'path_to_cfg',
                    #
                    #                 }
                    #          }
                    config = {}
                    contur = %kontur%
                    path = '%path_to_cfg%'
                    with open(path) as f:
                    	for line in f:
                    		if contur in line.split(':')[0]:
                    			prm = line.split(':')[0]
                    			val = line.split(':')[1]
                    			if '\n' not in val:
                    				val = line.split(':')[1]
                    			else:
                    				val = line.split(':')[1][:-1]
                    			config[prm.split('_')[0]] = val
                    print(config)
                    #print (slovar[%stend%][%kontur%][%namespase%])
                """.trimIndent()
            }
        }
    }
})
