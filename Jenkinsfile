pipeline {
    agent any

    stages {
        stage('Checkout SCM') {
            steps {
                git url: 'https://github.com/Irawancoy/xmartSpringBootBE.git', branch: 'main'
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
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t xmart .'
            }
        }
        stage('Docker Push') {
            steps {
                withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhub-password')]) {
                    sh '''
                    echo $dockerhub-password | docker login -u irawan123 --password-stdin
                    docker push xmart
                    '''
                }
            }
        }
    }

    post {
        always {
            sh 'echo Cleaning up...'
        }
    }
}
