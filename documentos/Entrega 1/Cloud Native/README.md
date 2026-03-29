# Projeto Maya RPG - Automação de Infraestrutura

Este projeto apresenta uma solução de automação em Shell Script (Bash) para o App Maya RPG, focado no agendamento e acompanhamento de sessões de fisioterapia. O objetivo é garantir a disponibilidade e a qualidade do produto conforme a norma ISO/IEC 25010.

## Arquivos

```text
Maya_RPG/
└── scripts/
    ├── setup.sh       → Configura o ambiente e instala o Java 17 
    ├── monitor.sh     → Mostra o uso de CPU, memória e disco
    ├── backup.sh      → Faz backup do projeto automaticamente 
    ├── gerenciar.sh   → Inicia, para e reinicia o backend 
    ├── backend_maya.jar → Arquivo demonstrativo (vazio) 
    ├── app_backend.log  → Registro de eventos e erros do sistema
    └── README.md      → Este arquivo aqui
```

---

## Como rodar

Antes de qualquer coisa, dê permissão de execução para os scripts:

```bash
chmod +x 
    ├── setup.sh      
    ├── monitor.sh
    ├── backup.sh    
    └── gerenciar.sh
```

---

## Os scripts

### setup.sh — Preparar o ambiente

Esse script automatiza a configuração inicial, instalando o OpenJDK 17 e configurando as variáveis de ambiente no seu .bashrc. Isso garante a Compatibilidade e Portabilidade do sistema.

```bash
bash setup.sh
```

Você só precisa rodar uma vez quando for configurar a máquina do zero.

---

### monitor.sh — Ver como está o sistema

Mostra um relatório rápido com as métricas de hardware. Essencial para validar a Eficiência de Desempenho.

```bash
bash monitor.sh
```

Exemplo do que aparece:

```
--- RELATÓRIO DE MONITORAMENTO ---
Data/Hora: Sab Mar 28 21:00:00 UTC 2026

[Uso de CPU - Load Average]
 0.15, 0.10, 0.09

[Uso de Memória RAM]
Usado: 512MB / Total: 2048MB

[Uso de Disco]
Espaço ocupado: 45% de 20G
----------------------------------
```

---

### backup.sh — Salvar o projeto

Compacta a pasta do projeto e salva em ~/backups com a data e hora no nome, garantindo a Confiabilidade e a Segurança dos dados sensíveis de saúde.

```bash
bash backup.sh
```

O arquivo gerado fica assim:

```
~/backups/backup_codigo_2026-03-28_21-05.tar.gz
```

Para automatizar, adicione esta linha ao crontab -e:

```
00 03 * * * /home/seu-usuario/backup.sh
```

---

### gerenciar.sh — Controlar o backend

Serve para iniciar, parar ou ver o status do servidor. Possui lógica de autorrecuperação: se o sistema cair, o script tenta reiniciar sozinho.

```bash
./gerenciar.sh start    # inicia
./gerenciar.sh stop     # para
./gerenciar.sh status   # verifica — reinicia automaticamente se estiver offline
```

---

## Observação Técnica

O arquivo backend_maya.jar incluído nesta entrega é apenas um placeholder demonstrativo (vazio).

```
Ao executar o comando status, o script detectará que o sistema está offline e tentará o restart.

O erro Invalid or corrupt jarfile aparecerá no log app_backend.log.

Isso prova que a lógica de automação e monitoramento está funcionando corretamente.
```

### Ambiente Ubuntu Server e Portabilidade

Para atender às características de **Compatibilidade** e **Portabilidade** da norma ISO 25010, os scripts foram desenhados para o ecossistema Ubuntu:

---

## Qualidade de Software (ISO 25010)

Os scripts foram desenvolvidos considerando as seguintes características:

```
Adequação Funcional: Funções que atendem às necessidades de agendamento.


Confiabilidade: Recuperação automática de falhas.


Segurança: Proteção de informações contra acessos não autorizados via backups
```

---
