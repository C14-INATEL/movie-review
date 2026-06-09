pipeline {
    agent none

    triggers { githubPush() }

    environment {
        SMTP_PORT = "${env.SMTP_PORT ?: '587'}"
        REPO      = 'C14-INATEL/movie-review'
        JAR_NAME  = 'movie-review-backend-0.0.1-SNAPSHOT.jar'
    }

    options {
        timeout(time: 30, unit: 'MINUTES')
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

    stages {
stage('Testes') {
    agent {
        docker {
            image 'maven:3.9.6-eclipse-temurin-17'
            args '-v $HOME/.m2:/root/.m2'
            reuseNode true
        }
    }

    steps {
        dir('backend') {
            sh 'mvn test -B'
        }
    }

    post {
        always {
            junit 'backend/target/surefire-reports/TEST-*.xml'

            archiveArtifacts(
                artifacts: 'backend/target/surefire-reports/TEST-*.xml',
                allowEmptyArchive: true
            )
        }
    }
}

        // Stage 2 — comitado por: Warley Ruivo
        stage('Cobertura de Código') {
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-17'
                    args  '-v $HOME/.m2:/root/.m2'
                    reuseNode true
                }
            }
            steps {
                dir('backend') { sh 'mvn verify -B -DskipTests=false' }
            }
            post {
                always {
                    recordCoverage(tools: [[parser: 'JACOCO',
                                           pattern: 'backend/target/site/jacoco/jacoco.xml']])
                }
            }
        }

    }

    post {
        success {
            sh '[ -n "$SMTP_HOST" ] && bash scripts/send_email.sh SUCCESS "$BUILD_URL" || echo "SMTP nao configurado, pulando notificacao"'
        }
        failure {
            sh '[ -n "$SMTP_HOST" ] && bash scripts/send_email.sh FAILURE "$BUILD_URL" || echo "SMTP nao configurado, pulando notificacao"'
        }
    }
}
