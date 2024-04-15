pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage('Build') {
            steps {

                bat 'mvn -Dmaven.test.skip=true install'
            }
        }
    }
}

