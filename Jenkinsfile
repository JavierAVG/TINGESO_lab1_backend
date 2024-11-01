pipeline {
    agent any
    tools{
        maven '3.9.9'
    }
    stages{
        stage('Build maven'){
            steps{
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/amigo1975/book-service']])
                sh 'mvn clean package'
            }
        }

        stage('Unit Tests') {
            steps {
                // Run Maven 'test' phase. It compiles the test sources and runs the unit tests
                sh 'mvn test'
            }
        }

        stage('Build docker image'){
            steps{
                script{
                    sh 'docker build -t javieravg/mortgage-backend:latest .'
                }
            }
        }
        stage('Push image to Docker Hub'){
            steps{
                script{
                   withCredentials([string(credentialsId: 'dhpswid', variable: 'dhpsw')]) {
                        sh 'docker login -u javieravg -p %dhpsw%'
                   }
                   sh 'docker push javieravg/mortgage-backend:latest'
                }
            }
        }
    }
}