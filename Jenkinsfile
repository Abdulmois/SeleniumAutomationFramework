pipeline {
    agent any

    tools {
        jdk 'JDK-17'
        maven 'Maven-3'
    }

    stages {
        stage('Build & Test') {
            steps {
                dir('SeleniumAutomationFramework') {
                    bat 'java -version'
                    bat 'javac -version'
                    bat 'mvn clean test'
                }
            }
        }
    }
}



