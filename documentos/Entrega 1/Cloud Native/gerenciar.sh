#!/bin/bash
# Script robusto com travas de segurança [cite: 97, 178]
set -euo pipefail

# Configurações do serviço
JAR_FILE="backend_maya.jar"
LOG_FILE="app_backend.log"

case "${1:-status}" in
    start)
        echo "Iniciando Backend..."
        # Executa em segundo plano e salva logs de erro e saída [cite: 65]
        nohup java -jar "$JAR_FILE" > "$LOG_FILE" 2>&1 &
        echo "Servidor rodando em background."
        ;;

    stop)
        echo "Parando Backend..."
        # Finaliza o processo pelo nome do arquivo [cite: 211]
        pkill -f "$JAR_FILE" || echo "Aviso: Nenhum processo encontrado."
        ;;

    status)
        # Verifica se o processo está ativo no sistema [cite: 216]
        if pgrep -f "$JAR_FILE" > /dev/null; then
            echo "Status: ONLINE"
        else
            echo "Status: OFFLINE. Tentando restart automático..."
            # Chama o próprio script para iniciar o serviço 
            bash "$0" start
        fi
        ;;

    *)
        # Mensagem de ajuda para parâmetros inválidos [cite: 175]
        echo "Uso: $0 {start|stop|status}"
        exit 1
        ;;
esac
