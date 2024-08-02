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
                // Ganti perintah bat menjadi sh
                sh 'echo Installing tools...'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=xmart-java -Dsonar.projectName="xmart-java" -Dsonar.host.url=http://sonarqube:9000 -Dsonar.token=sqp_2b2f3456ee7fee55dbfd218fe7285f17c5a09780'
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
                sh 'docker build -t xmart-java .'
            }
        }
        stage('Docker Push') {
            steps {
                sh 'docker push xmart-java'
            }
        }
        stage('Docker Run') {
            steps {
                sh 'docker run -d -p 8080:8080 xmart-java'
            }
        }
    }

    post {
        always {
            sh 'echo Cleaning up...'
        }
    }
}
