pipeline {
    agent any

    tools {
        git 'Default'
        maven 'jenkins-maven'
        jdk 'jdk'
    }

    environment {
        BUILD_NUMBER_ENV = "${env.BUILD_NUMBER}"
        TEXT_SUCCESS_BUILD = "[#${env.BUILD_NUMBER}] Project Name : ${JOB_NAME} is Success"
        TEXT_FAILURE_BUILD = "[#${env.BUILD_NUMBER}] Project Name : ${JOB_NAME} is Failure"
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
    }
    post{
        always{
            echo 'This will always run'
        }
         success {
            script{
                 withCredentials([string(credentialsId: 'telegram-token', variable: 'TOKEN'), string(credentialsId: 'telegram-chat-id', variable: 'CHAT_ID')]) {
                    bat ''' curl -s -X POST https://api.telegram.org/bot"%TOKEN%"/sendMessage -d chat_id="%CHAT_ID%" -d text="%TEXT_SUCCESS_BUILD%" '''
                 }
            }
        }
        failure{
            script{
                 withCredentials([string(credentialsId: 'telegram-token', variable: 'TOKEN'), string(credentialsId: 'telegram-chat-id', variable: 'CHAT_ID')]) {
                    bat ''' curl -s -X POST https://api.telegram.org/bot"%TOKEN%"/sendMessage -d chat_id="%CHAT_ID%" -d text="%TEXT_FAILURE_BUILD%" '''
                 }
            }
        }
   
    }
    
}
