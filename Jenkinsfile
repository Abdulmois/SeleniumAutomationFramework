pipeline {
    agent any
    tools {
        maven 'Maven-Local'  // your Maven installation name
        jdk 'Java-21'        // if needed
    }

    stages {
        stage('Checkout SCM') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // Run Maven in root where pom.xml exists
                bat '"C:\\ProgramData\\Jenkins\\.jenkins\\tools\\hudson.tasks.Maven_MavenInstallation\\Maven-Local\\bin\\mvn" clean test'
            }
        }

        stage('Publish Report') {
            steps {
                publishHTML(target: [
                    reportDir: 'test-output',   // your TestNG output folder
                    reportFiles: 'index.html',  // ensure TestNG generates this
                    reportName: 'TestNG Report'
                ])
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished.'
        }
    }
}




