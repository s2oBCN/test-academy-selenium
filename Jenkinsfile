#!/usr/bin/env groovy

node('master')
{
    stage('Prepare') {
        deleteDir()
        parallel Checkout: {
            checkout scm
        }, 'Run Zalenium': {
            dockerCmd '''run -d --name zalenium -p 4444:4444 \
                                -v /var/run/docker.sock:/var/run/docker.sock \
                                --network="host" \
                                --privileged dosel/zalenium start \
                                --videoRecordingEnabled true \
                                --chromeContainers 1 \
                                --firefoxContainers 0'''
        }
    }

    stage('Compile')
    {
        gradle "assemble"
    }

    stage("Test")
    {
        gradle "test"

        dockerCmd 'stop zalenium'
        dockerCmd 'rm zalenium'
    }

    stage("Publish")
    {
        junit testResults: "target/site/serenity/SERENITY-JUNIT-*.xml", allowEmptyResults: true

        gradle "aggregate"
        publishHTML(target: [
                reportName           : "Serenity",
                reportDir            : "target/site/serenity",
                reportFiles          : 'index.html',
                keepAll              : true,
                alwaysLinkToLastBuild: true,
                allowMissing         : true
        ])
    }
}

def gradle(String tasks, String switches = null) {
    String gradleCommand = "";
    gradleCommand += './gradlew '
    gradleCommand += tasks

    if (switches != null) {
        gradleCommand += ' '
        gradleCommand += switches
    }

    return sh (script:gradleCommand.toString(), returnStatus: true)
}

def dockerCmd(args) {
    assert args != null
    return sh(script: "docker ${args}", returnStdout: true)
}
