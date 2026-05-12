#!/bin/bash
echo "--- MONITORAMENTO MAYA RPG (CLOUD NATIVE) ---"
date
echo -e "\n[Status dos Containers]"
docker ps --format "table {{.Names}}\t{{.Status}}\t{{.Ports}}"
echo -e "\n[Uso de Recursos]"
docker stats --no-stream
