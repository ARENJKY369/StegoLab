#!/usr/bin/env sh
set -eu
BASE="${1:-http://localhost:8000}"
curl -fsS "$BASE/api/health" >/dev/null
curl -fsS "$BASE/api/capabilities" >/dev/null
curl -fsS -X POST "$BASE/api/text/encode/zero-width" -H 'Content-Type: application/json' -d '{"text":"carrier","message":"hello"}' >/dev/null
curl -fsS -X POST "$BASE/api/text/scan" -H 'Content-Type: application/json' -d '{"text":"carrier"}' >/dev/null
curl -fsS -X POST "$BASE/api/network/dns" -H 'Content-Type: application/json' -d '{"message":"hello"}' >/dev/null
curl -fsS -X POST "$BASE/api/crypto/encrypt" -H 'Content-Type: application/json' -d '{"text":"hello","password":"strong-password"}' >/dev/null
curl -fsS -X POST "$BASE/api/utilities/codec" -H 'Content-Type: application/json' -d '{"value":"hello","mode":"encode-base64"}' >/dev/null
echo "StegLab Java API smoke tests passed"
