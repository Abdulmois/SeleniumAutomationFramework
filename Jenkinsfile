pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven'
    }

    stages {
        stage('Build & Test') {
            steps {
                bat 'java -version'
                bat 'javac -version'
                bat 'mvn clean test'
            }
        }
    }
}



