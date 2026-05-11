#!/bin/bash
# Backup direto do container do banco
TIMESTAMP=$(date +%F_%H-%M)
mkdir -p ~/backups
docker exec container-maya-db pg_dump -U admin_maya maya_db_production > ~/backups/backup_maya_$TIMESTAMP.sql
echo "Backup do banco de dados realizado em ~/backups/backup_maya_$TIMESTAMP.sql"
