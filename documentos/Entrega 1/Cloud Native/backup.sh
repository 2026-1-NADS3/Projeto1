#!/bin/bash
set -euo pipefail

# backup.sh: Backup do projeto e persistência local [cite: 8, 21]

DESTINO="$HOME/backups"
ORIGEM="$HOME/projeto_maya" # Ajuste para o caminho do seu projeto ADS
DATA=$(date +%Y-%m-%d_%H-%M)

# Criar pasta de destino se não existir [cite: 109]
[ ! -d "$DESTINO" ] && mkdir -p "$DESTINO"

if [ -d "$ORIGEM" ]; then
    echo "Iniciando backup em $DATA..."
    # tar -czf: c=criar, z=comprimir (gzip), f=arquivo
    tar -czf "$DESTINO/backup_codigo_$DATA.tar.gz" "$ORIGEM" 2>/dev/null
    echo "Backup concluído com sucesso em: $DESTINO"
else
    echo "AVISO: Diretório de origem $ORIGEM não encontrado. Backup de código pulado."
fi
