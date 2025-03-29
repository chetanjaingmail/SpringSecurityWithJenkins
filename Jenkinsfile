pipeline {
    agent any

    environment {
        DOCKER_COMPOSE_FILE = "docker-compose.yml"
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/chetanjaingmail/SpringSecurityWithJenkins'
            }
        }

        stage('Build & Test') {
            steps {
                // Use mvnw.cmd with the appropriate Maven goals
                bat '.\\mvnw.cmd clean package -DskipTests'  // Windows Maven wrapper with clean and package goal
            }
        }

        stage('Start Containers') {
            steps {
                bat 'docker-compose pull'  // Pull latest images
                bat 'docker-compose up -d --build'  // Start containers
            }
        }

        stage('Verify Running Containers') {
            steps {
                bat 'docker ps --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"'
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully! The containers are running.'
        }

        failure {
            echo 'Pipeline failed!'
        }
    }
}
