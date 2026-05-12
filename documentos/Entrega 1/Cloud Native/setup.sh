#!/bin/bash
# set -e: para se algo falhar | -u: erro em variáveis não definidas | -o pipefail: erro em pipes
set -euo pipefail

# setup.sh: Automatiza instalação de dependências (Java, SDK, build tools)
# Requisito: Cloud Native - Funcionalidades obrigatórias 

erro() {
    echo "ERRO: $1" >&2
    exit 1
}

echo "Iniciando o setup do sistema para Clínica Maya..."

# 1. Atualizar repositórios
sudo apt update -y || erro "Falha ao atualizar repositórios."

# 2. Instalar Java 17
echo "Instalando OpenJDK 17..."
sudo apt install -y openjdk-17-jdk || erro "Falha ao instalar Java."

# 3. Criar diretório para Android SDK usando variável de segurança [cite: 74]
SDK_DIR="$HOME/android-sdk"
if [ ! -d "$SDK_DIR" ]; then
    mkdir -p "$SDK_DIR"
    echo "Diretório do SDK criado em: $SDK_DIR"
fi

# 4. Instalar ferramentas auxiliares
sudo apt install -y wget unzip || erro "Falha ao instalar wget/unzip."

# 5. Configurar Variáveis de Ambiente (Adiciona apenas se não existir)
if ! grep -q "JAVA_HOME" "$HOME/.bashrc"; then
    echo "Configurando variáveis de ambiente no .bashrc..."
    echo 'export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64' >> "$HOME/.bashrc"
    echo 'export PATH=$PATH:$JAVA_HOME/bin' >> "$HOME/.bashrc"
fi

echo "Setup finalizado! Versão do Java instalada:"
java -version
