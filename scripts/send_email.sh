#!/usr/bin/env bash
# Uso: send_email.sh <SUCCESS|FAILURE> <BUILD_URL>
set -euo pipefail

STATUS="${1:-UNKNOWN}"
BUILD_URL="${2:-}"

SUBJECT="[movie-review] Build ${STATUS}: Job ${JOB_NAME:-?} #${BUILD_NUMBER:-?}"
RECIPIENT="${NOTIFY_EMAIL:-}"
FROM="${SMTP_FROM:-jenkins@movie-review.local}"

if [[ -z "$RECIPIENT" ]]; then
    echo "NOTIFY_EMAIL nao configurado — pulando notificacao."
    exit 0
fi

BODY="Resultado: ${STATUS}
Job:           ${JOB_NAME:-?}
Build:         ${BUILD_NUMBER:-?}
Branch:        ${GIT_BRANCH:-?}
URL:           ${BUILD_URL}
Log completo:  ${BUILD_URL}console"

curl --silent \
     --url "smtp://${SMTP_HOST}:${SMTP_PORT:-1025}" \
     --mail-from "$FROM" \
     --mail-rcpt "$RECIPIENT" \
     --upload-file - << EOF
From: Jenkins <${FROM}>
To: ${RECIPIENT}
Subject: ${SUBJECT}

${BODY}
EOF

echo "Email enviado para ${RECIPIENT}"
