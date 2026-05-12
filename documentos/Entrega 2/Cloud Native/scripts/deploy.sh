#!/bin/bash
# Move para a pasta onde o script está e sobe um nível (raiz da Entrega 2)
cd "$(dirname "$0")/.."

echo "Iniciando Deploy Cloud Native..."
docker compose down
docker compose up -d --build

echo "Limpando imagens antigas..."
docker image prune -f

echo "Sistema online em http://localhost:8080"
