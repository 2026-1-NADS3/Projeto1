#!/bin/bash
echo "Iniciando Deploy Cloud Native..."
docker compose down
docker compose up -d --build
echo "Limpando imagens antigas..."
docker image prune -f
echo "Sistema online em http://localhost:8080"
