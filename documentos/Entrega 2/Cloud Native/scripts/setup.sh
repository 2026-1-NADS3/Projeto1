#!/bin/bash
# setup.sh

# Verifica privilégios
if [ "$EUID" -ne 0 ]; then
    echo "Erro: Execute como root (sudo)."
    exit 1
fi

# Instalação de dependências
apt update && apt install -y docker.io docker-compose-v2

# Configuração de permissões
usermod -aG docker $(logname)
chmod +x scripts/*.sh

echo "Setup concluído. Reinicie a sessão para aplicar as permissões."
