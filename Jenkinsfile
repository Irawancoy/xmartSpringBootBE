pipeline {
    agent any

    tools {
        git 'Default'
        maven 'jenkins-maven'
        jdk 'jdk'
    }

    stages {
        stage('Checkout SCM') {
            steps {
                git url: 'https://github.com/Irawancoy/xmartSpringBootBE.git', branch: 'main'
            }
        }
        stage('Tool Install') {
            steps {
                bat 'echo Installing tools...'
                bat 'mvn --version'
                bat 'java -version'
                bat '"C:\\Program Files\\Git\\bin\\git.exe" --version'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat """
                        mvn clean verify sonar:sonar ^
                        -Dsonar.projectKey=xmart ^
                        -Dsonar.projectName='xmart' ^
                        -Dsonar.host.url=http://localhost:9000 ^
                        -Dsonar.token=sqp_316cdde84ab989f3e5cb198f9bfb7093b80e366e
                    """
                }
            }
        }
        stage('Docker Push'){
            steps{
                script{
                    withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhub-passsword')]) {
                        bat 'docker login -u irawan123 -p %dockerhub-passsword%'
                        bat 'docker build -t irawancoy/xmart-spring-boot-be .'
                        bat 'docker push irawancoy/xmart-spring-boot-be'
                }
                  
            }
        }

    }
    }
    
    post {
        always {
            bat 'docker logout'
        }
    }
}
