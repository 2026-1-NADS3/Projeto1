#!/bin/bash
cd "$(dirname "$0")/.."

echo "Iniciando Deploy Cloud Native (Produção)..."

# Garante a porta 8080 como padrão
export PORTA_HOST=${PORTA_HOST:-8080}

# Agora o script gerencia o projeto 'producao' automaticamente
docker compose -p producao down
docker compose -p producao up -d --build

echo "Limpando imagens antigas..."
docker image prune -f

echo "Sistema online em http://localhost:$PORTA_HOST"
