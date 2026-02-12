pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

    stage('Build & Test') {
    steps {
        script {
            dir("${WORKSPACE}") { // root folder
                bat '"C:\\ProgramData\\Jenkins\\.jenkins\\tools\\hudson.tasks.Maven_MavenInstallation\\Maven-Local\\bin\\mvn" clean test'
            }
        }
    }
}



    }

    post {
        always {
            // TestNG HTML report
            publishHTML([
                allowMissing: true,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'test-output',
                reportFiles: 'index.html',
                reportName: 'TestNG Report'
            ])
        }
    }
}



