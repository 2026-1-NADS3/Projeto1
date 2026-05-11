#!/bin/bash
echo "Instalando dependências de Cloud Native..."
sudo apt update
sudo apt install docker.io docker-compose-v2 -y
sudo usermod -aG docker $USER
echo "Setup finalizado. Por favor, reinicie a sessão para aplicar as permissões do Docker."
