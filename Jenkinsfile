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

        // Só roda quando o merge chegou na main (não em builds de PR)
        // O quality gate já garante que, se o Sonar falhar, o pipeline para antes daqui
        stage('Publicar Release') {
            when { branch 'main' }
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-17'
                    args  '-v $HOME/.m2:/root/.m2'
                    reuseNode true
                }
            }
            steps {
                withCredentials([string(credentialsId: 'github-token', variable: 'GITHUB_TOKEN')]) {
                    sh '''
                        TAG_NAME="v${BUILD_NUMBER}"
                        JAR="backend/target/${JAR_NAME}"

                        RESPONSE=$(curl -sf -X POST \
                            -H "Authorization: token ${GITHUB_TOKEN}" \
                            -H "Content-Type: application/json" \
                            -d "{\"tag_name\":\"${TAG_NAME}\",\"target_commitish\":\"main\",\"name\":\"${TAG_NAME}\",\"body\":\"Build automatico #${BUILD_NUMBER}\"}" \
                            https://api.github.com/repos/${REPO}/releases)

                        RELEASE_ID=$(echo "$RESPONSE" | grep -o '"id":[0-9]*' | head -1 | grep -o '[0-9]*')

                        curl -sf -X POST \
                            -H "Authorization: token ${GITHUB_TOKEN}" \
                            -H "Content-Type: application/java-archive" \
                            --data-binary @"${JAR}" \
                            "https://uploads.github.com/repos/${REPO}/releases/${RELEASE_ID}/assets?name=movie-review-backend-${TAG_NAME}.jar"

                        echo "Release ${TAG_NAME} publicada: https://github.com/${REPO}/releases/tag/${TAG_NAME}"
                    '''
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
