#!/bin/bash
set -euo pipefail

# monitor.sh: Coletar métricas de CPU, RAM e Disco 

echo "--- RELATÓRIO DE MONITORAMENTO CLÍNICA MAYA ---"
echo "Data/Hora: $(date)"

# 1. Uso de CPU (Load Average) 
echo -e "\n[Uso de CPU - Load Average]"
uptime | awk -F'load average:' '{ print $2 }'

# 2. Uso de Memória RAM 
echo -e "\n[Uso de Memória RAM]"
free -m | grep "Mem:" | awk '{print "Usado: " $3 "MB / Total: " $2 "MB"}'

# 3. Uso de Disco (Partição raiz) 
echo -e "\n[Uso de Disco]"
df -h / | tail -1 | awk '{print "Espaço ocupado: " $5 " de " $2}'

echo -e "\n--------------------------------------------"
