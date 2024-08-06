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
        stage('Quality Gate') {
            steps {
                script {
                    def qg = waitForQualityGate()
                    def previousStatus = ''
                    while (qg.status == 'PENDING' || qg.status == 'IN_PROGRESS') {
                        qg = waitForQualityGate() // Poll for updated status
                        if (qg.status != previousStatus) {
                            echo "Quality Gate status changed: ${qg.status}"
                            previousStatus = qg.status
                        }
                        sleep(10) // Wait for 10 seconds before checking again
                    }
                    if (qg.status == 'OK') {
                        echo "Quality Gate passed: ${qg.status}"
                    } else {
                        error "Pipeline aborted due to Quality Gate failure: ${qg.status}"
                    }
                }
            }
        }
    }
    
    post {
        success {
            withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhub-password')]) {
                echo "Using Docker Hub password: ${env.dockerhub-password}"
              
            }
        }
    }
}
