pipeline {
    agent any
    tools{
        maven '3.9.9'
    }
    stages{
        stage('Build maven'){
            steps{
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/JavierAVG/TINGESO_lab1_backend.git']])
                sh 'mvn clean package'
            }
        }

        /*
        stage('Unit Tests') {
            steps {
                // Run Maven 'test' phase. It compiles the test sources and runs the unit tests
                sh 'mvn test'
            }
        }
        */

        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t javieravg/mortgage-backend:latest .'
                }
            }
        }

        stage('Push image to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'dhpswid', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh 'docker login -u $DOCKER_USER -p $DOCKER_PASSWORD'
                    }
                    sh 'docker push javieravg/mortgage-backend:latest'
                }
            }
        }
    }
}