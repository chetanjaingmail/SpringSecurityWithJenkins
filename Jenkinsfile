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
                bat 'mvnw.cmd'  // Give execution permission
                bat '.\\mvnw.cmd clean package -DskipTests'  // Use mvnw.cmd for Windows
            }
        }

        stage('Start Containers') {
            steps {
                bat 'docker-compose pull'  // Pull latest images
                bat 'docker-compose up -d --build --no-cache'  // Start containers
            }
        }

        stage('Verify Running Containers') {
            steps {
                bat 'docker ps --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"'
            }
        }

        stage('Cleanup Unused Resources') {
            steps {
                bat 'docker system prune -f'
            }
        }
    }

    post {
        always {
            bat 'docker-compose down -v'  // Shutdown and remove volumes
        }
    }
}
