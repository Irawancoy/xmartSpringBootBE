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
                        -Dsonar.login=sqp_065a07c73cba8f74728ef2eba17f074253336fe4
                    """
                }
            }
        }
        stage("Quality Gate") {
            steps {
                script {
                    def qualityGate = waitForQualityGate()
                    if (qualityGate.status != 'OK') {
                        error "Pipeline aborted due to quality gate failure: ${qualityGate.status}"
                    }
                }
                echo 'Quality Gate Completed'
            }
        }
    }

    post {
        always {
            bat 'echo Cleaning up...'
        }
    }
}
