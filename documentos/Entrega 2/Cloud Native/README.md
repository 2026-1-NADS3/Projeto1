<div align="center">

![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL_15-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Java](https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Bash](https://img.shields.io/badge/Bash-4EAA25?style=for-the-badge&logo=gnubash&logoColor=white)

<br/>

# Cloud Native — Entrega 2
### Sistemas Operacionais e Arquiteturas Cloud Native

*Clínica Maya Yoshiko Yamamoto · PI 3ADS · FECAP 2026*

</div>

---

## Sobre a Infraestrutura

Esta pasta contém os artefatos de **Cloud Native** do Projeto Maya. O objetivo principal foi containerizar a API Rest (Java/Maven) e o Banco de Dados (PostgreSQL), garantindo um ambiente isolado, replicável e resiliente, utilizando automação via Shell Script para gestão de ciclo de vida.

---

## Estrutura de Arquivos

```text
Cloud Native/
│
├──  database/            # Dockerfile customizado e init scripts do PostgreSQL
├──  scripts/             # Scripts de automação Bash (centralizados)
│
├── Dockerfile           # Build multi-stage (Maven -> JRE 17 Alpine)
├── docker-compose.yml   # Orquestração de containers, redes e volumes
├── RELATORIO_TECNICO.md # Documentação detalhada dos atributos de qualidade
└── README.md            # Guia de execução e visão geral (este arquivo)
```

---

## Execução Rápida

Para subir o ambiente completo no Linux Ubuntu, execute os scripts na sequência abaixo:

**1. Instalar dependências (Docker/Compose)**

```bash
./setup.sh
```

**2. Realizar o Deploy completo**

```bash
./deploy.sh
```

**3. Validar execução**

```bash
docker ps
```

---

## Scripts de Automação

Seguindo os requisitos de Sistemas Operacionais, foram desenvolvidos scripts para automatizar tarefas críticas:

| Validação | Resultado | Descrição Técnica |
|-----------|:---------:|:-----------------:|
| `setup.sh` | Ambiente | Verifica e instala o runtime do Docker e Docker Compose. |
| `deploy.sh` | Orquestração | Gerencia o build das imagens, criação de redes bridge e volumes. |
| `monitor.sh` | Saúde | Coleta métricas de CPU, Memória e status dos containers em tempo real. |
| `backup.sh` | Segurança | Realiza o dump estruturado (pg_dump) para persistência externa. |

---

## Resultados de Validação (QA)

A infraestrutura foi submetida a testes de estresse e persistência em 11/05/2026 com sucesso:

| Teste | ISO 25010 | Evidência | Resultado |
|-------|:---------:|:---------:|:---------:|
| Persistência de Dados | Confiabilidade | Dados sobrevivem ao `docker compose down`  | ✅ PASS |
| Conectividade | Interoperabilidade | Ping interno entre API e Banco (0% loss)  | ✅ OK | 
| Isolamento | Segurança | Banco de dados inacessível fora da `maya-network` | ✅ OK |
| Eficiência | Performance | Monitoramento ativo via script Shell | ✅ Ativo |
| Recuperabilidade | Segurança | Backup .sql gerado e validado com 1.9KB | ✅ OK |

---

## Estratégia Cloud Native Aplicada

- Multi-stage Build: O Dockerfile utiliza uma etapa de build (Maven) e outra de runtime (JRE Alpine) para reduzir o tamanho da imagem final e aumentar a segurança.

- Persistência (Volumes): Utilização de volumes nomeados para garantir que os prontuários e agendamentos não sejam perdidos.

- Rede Privada: API e Banco comunicam-se via DNS interno do Docker, protegendo a camada de dados.

- Integração de Build: Pipeline de compilação Maven totalmente integrado ao Docker, gerando artefatos executáveis e prontos para produção.

---

## 📚 Documentação Técnica

| Documento | Descrição |
|:----------|:----------|
| [Relatório de Validação](./RELATORIO_TECNICO.md) | Evidências de testes, conectividade, persistência e prints do terminal. |

---
