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
            def mvnHome = tool name: 'Maven-Local', type: 'maven'
            
            dir('Framework') {
                bat "\"${mvnHome}\\bin\\mvn\" clean test"
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



