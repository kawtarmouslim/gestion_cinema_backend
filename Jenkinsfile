pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {
        stage('Build') {
            steps {
                // Étapes de construction du projet avec Maven
                sh 'mvn clean install'
            }
        }
    }
}
