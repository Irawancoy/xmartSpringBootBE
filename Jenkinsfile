pipeline {
    agent any

    stages {
        stage('Checkout SCM') {
            steps {
                git url: 'https://github.com/Irawancoy/xmartSpringBootBE.git', branch: 'main'
            }
        }
        stage('Tool Install') {
            steps {
                // Menggunakan perintah sh untuk shell script
                sh 'echo Installing tools...'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh """
                    mvn clean verify sonar:sonar \
   -Dsonar.projectKey=xmart \
   -Dsonar.projectName='xmart' \
   -Dsonar.host.url=http://localhost:9000 \
   -Dsonar.token=sqp_065a07c73cba8f74728ef2eba17f074253336fe4
                    """
                }
            }
        }
        stage("Quality Gate") {
            steps {
                waitForQualityGate abortPipeline: true
                echo 'Quality Gate Completed'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t xmart .'
            }
        }
        stage('Docker Push') {
            steps {
                sh 'docker push xmart'
            }
        }
        stage('Docker Run') {
            steps {
                sh 'docker run -d -p 8080:8080 xmart'
            }
        }
    }

    post {
        always {
            sh 'echo Cleaning up...'
        }
    }
}
