#!/usr/bin/env bash
# Uso: send_email.sh <SUCCESS|FAILURE> <BUILD_URL>
set -euo pipefail

STATUS="${1:-UNKNOWN}"
BUILD_URL="${2:-}"

SUBJECT="[movie-review] Build ${STATUS}: Job ${JOB_NAME:-?} #${BUILD_NUMBER:-?}"
RECIPIENT="${NOTIFY_EMAIL:-}"

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

echo "$BODY" | mail -s "$SUBJECT" "$RECIPIENT"
