#!/bin/bash
TIMESTAMP=$(date +%F_%H-%M)
# Garante que a pasta de backups exista na Home do seu Ubuntu
mkdir -p ~/backups

echo "Iniciando backup..."
# O docker exec funciona de qualquer lugar, desde que o container esteja rodando
docker exec container-maya-db pg_dump -U admin_maya maya_db_production > ~/backups/backup_maya_$TIMESTAMP.sql

echo "Backup concluído: ~/backups/backup_maya_$TIMESTAMP.sql"
