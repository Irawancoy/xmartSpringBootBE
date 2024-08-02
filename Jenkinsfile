// withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhub-password')]) {
//     // some block
// }

pipeline {
    agent any
    tools {
        maven 'jenkins-maven'
    }

    stages {
        stage('Git Checkout') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Irawancoy/xmartSpringBootBE.git']])
                bat 'mvn clean install'
                echo 'Git Checkout Completed'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat 'mvn clean package'
                    bat '''mvn clean verify sonar:sonar -Dsonar.projectKey=xmart-java -Dsonar.projectName="xmart-java" -Dsonar.host.url=http://localhost:9000'''
                    echo 'SonarQube Analysis Completed'
                }
            }
        }
        stage('Quality Gate') {
            steps {
                waitForQualityGate abortPipeline: true
                echo 'Quality Gate Completed'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    bat 'docker build -t irawan/xmart-java .'
                    echo 'Build Docker Image Completed'
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhub-password')]) {
                        bat '''docker login -u irawan123 -p "%dockerhub-password%"'''
                    }
                    bat 'docker push irawan/xmart-java'
                    echo 'Docker Push Completed'
                }
            }
        }

        stage('Docker Run') {
            steps {
                script {
                    bat 'docker run -d --name xmart-java -p 8099:8080 irawan/xmart-java'
                    echo 'Docker Run Completed'
                }
            }
        }
    }
    
    post {
        always {
            script {
                bat 'docker logout'
                echo 'Docker Logout Completed'
            }
        }
    }
}
