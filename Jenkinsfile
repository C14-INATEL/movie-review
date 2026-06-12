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
                                           pattern: 'backend/target/site/jacoco/jacoco.xml']],
                                  sourceDirectories: [[path: 'backend/src/main/java']])
                }
            }
        }

        stage('SonarQube Analysis') {
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-17'
                    args  '-v $HOME/.m2:/root/.m2 --network movie-review_default'
                    reuseNode true
                }
            }
            steps {
                withSonarQubeEnv('SonarCloud') {
                    sh '''mvn -f backend/pom.xml sonar:sonar -B \
                          -Dsonar.projectKey=com.moviereview:movie-review-backend \
                          -Dsonar.projectBaseDir=. \
                          -Dsonar.coverage.jacoco.xmlReportPaths=backend/target/site/jacoco/jacoco.xml'''
                }
            }
        }

        stage('Quality Gate') {
            agent none
            steps {
                timeout(time: 5, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        stage('Build / Empacotamento') {
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-17'
                    args  '-v $HOME/.m2:/root/.m2'
                    reuseNode true
                }
            }
            steps {
                dir('backend') { sh 'mvn package -B -DskipTests' }
            }
            post {
                success {
                    archiveArtifacts artifacts: 'backend/target/*.jar', fingerprint: true
                }
            }
        }

        // Só roda quando o merge chegou na main (não em builds de PR)
        // O quality gate já garante que, se o Sonar falhar, o pipeline para antes daqui
        stage('Publicar Release') {
            when { branch 'main' }
            environment {
                GITHUB_CREDS = credentials('github-token')
            }
            agent {
                docker {
                    image 'maven:3.9.6-eclipse-temurin-17'
                    args  '-v $HOME/.m2:/root/.m2'
                    reuseNode true
                }
            }
            steps {
                sh '''
                    TAG_NAME="v${BUILD_NUMBER}"
                    JAR="backend/target/${JAR_NAME}"

                    printf '{"tag_name":"%s","target_commitish":"main","name":"%s","body":"Build automatico #%s"}' "${TAG_NAME}" "${TAG_NAME}" "${BUILD_NUMBER}" > /tmp/gh_payload.json

                    curl -sf -X POST \
                        -H "Authorization: token ${GITHUB_CREDS_PSW}" \
                        -H "Content-Type: application/json" \
                        -d @/tmp/gh_payload.json \
                        "https://api.github.com/repos/${REPO}/releases" > /tmp/gh_release.json \
                        || { echo "ERRO: falha ao criar release na API do GitHub"; cat /tmp/gh_release.json; exit 1; }

                    RELEASE_ID=$(grep -o '"id":[^,}]*' /tmp/gh_release.json | head -1 | tr -dc 0-9) || true

                    if [ -z "$RELEASE_ID" ]; then
                        echo "ERRO: ID da release nao encontrado. Resposta da API:"
                        cat /tmp/gh_release.json
                        exit 1
                    fi

                    curl -sf -X POST \
                        -H "Authorization: token ${GITHUB_CREDS_PSW}" \
                        -H "Content-Type: application/java-archive" \
                        --data-binary @"${JAR}" \
                        "https://uploads.github.com/repos/${REPO}/releases/${RELEASE_ID}/assets?name=movie-review-backend-${TAG_NAME}.jar" \
                        || { echo "ERRO: falha ao fazer upload do JAR"; exit 1; }

                    echo "Release ${TAG_NAME} publicada: https://github.com/${REPO}/releases/tag/${TAG_NAME}"
                '''
            }
        }
    }

    post {
        success {
            node('built-in') {
                checkout scm
                sh '[ -n "$SMTP_HOST" ] && bash scripts/send_email.sh SUCCESS "$BUILD_URL" || echo "SMTP nao configurado, pulando notificacao"'
            }
        }
        failure {
            node('built-in') {
                checkout scm
                sh '[ -n "$SMTP_HOST" ] && bash scripts/send_email.sh FAILURE "$BUILD_URL" || echo "SMTP nao configurado, pulando notificacao"'
            }
        }
    }
}
