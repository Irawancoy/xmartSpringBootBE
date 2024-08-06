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
            def taskId = 'dc9158f2-c1f8-4eee-8afc-ae49876162b2' // Ganti dengan ID tugas yang tepat
            def timeout = 10 // Timeout dalam detik
            def elapsed = 0
            def interval = 10 // Interval polling dalam detik

            while (elapsed < timeout) {
                def response = sh(script: "curl -u sqp_316cdde84ab989f3e5cb198f9bfb7093b80e366e: http://localhost:9000/api/ce/task?id=${taskId}", returnStdout: true).trim()
                def json = readJSON(text: response)
                def status = json.task.status
                
                echo "Quality Gate status: ${status}"
                
                if (status == 'SUCCESS') {
                    echo "Quality Gate Passed"
                    return
                } else if (status == 'FAILURE') {
                    error "Pipeline aborted due to quality gate failure: ${status}"
                }
                
                sleep(interval)
                elapsed += interval
            }
            
            error "Pipeline aborted due to timeout waiting for Quality Gate"
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
