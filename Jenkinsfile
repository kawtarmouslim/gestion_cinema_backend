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
         stage('Test'){
                      steps{
                          bat 'mvn test -Dtest=ClientServiceImpTest'
                          bat 'mvn test -Dtest=CinemaServiceImplTest'
                          bat 'mvn test -Dtest=FilmServiceImpTest'
                      }
                }
    }
}

