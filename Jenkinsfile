pipeline {
    agent any

    tools {
        maven 'Maven-3'
    }

    stages {
        stage('Build & Test') {
            steps {
                dir('SeleniumAutomationFramework') {
                    bat 'mvn clean test'
                }
            }
        }
    }
}



