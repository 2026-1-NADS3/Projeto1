#!/bin/bash

# Nome do container agora segue o padrão do projeto 'producao'
CONTAINER_NAME="producao-maya-db-server-1"
BACKUP_DIR="$HOME/backups"
DATE=$(date +%Y-%m-%d_%H-%M)
FILE_NAME="backup_maya_${DATE}.sql"

# Cria a pasta de backup se ela não existir
mkdir -p "$BACKUP_DIR"

echo "Iniciando backup do banco de dados (Projeto: producao)..."

# Executa o dump e verifica se o container existe antes
if docker ps --format '{{.Names}}' | grep -q "$CONTAINER_NAME"; then
    docker exec $CONTAINER_NAME pg_dump -U admin_maya maya_db_production > "$BACKUP_DIR/$FILE_NAME"
    
    if [ $? -eq 0 ]; then
        echo "Backup concluído com sucesso!"
        echo "Arquivo: $BACKUP_DIR/$FILE_NAME"
        echo "Tamanho: $(ls -lh "$BACKUP_DIR/$FILE_NAME" | awk '{print $5}')"
    else
        echo "❌ Erro durante a geração do dump SQL."
        rm "$BACKUP_DIR/$FILE_NAME" # Remove o arquivo vazio em caso de erro
    fi
else
    echo "❌ Erro: O container $CONTAINER_NAME não está rodando."
    echo "Dica: Rode o ./scripts/deploy.sh primeiro."
fi
