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
