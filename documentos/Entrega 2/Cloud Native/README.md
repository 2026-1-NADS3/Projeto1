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

### Diferenciais Técnicos:
- **Orquestração Inteligente:** Docker Compose configurado para nomes dinâmicos e isolamento de rede.
- **Portas Parametrizadas:** Flexibilidade para rodar em diferentes portas via variáveis de ambiente.
- **Persistence First:** Garantia de que os dados críticos da clínica sobrevivem ao ciclo de vida dos containers.

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

## Execução e Deploy

Para subir o ambiente completo no Linux Ubuntu, execute os scripts na sequência abaixo:

* **1. Preparar Ambiente (Instalação e Permissões)

```bash
sudo ./scripts/setup.sh
```

* **2. Deploy de Produção (Padrão - Porta 8080)

```bash
./scripts/deploy.sh
```

* **3. Deploy de Staging (Opcional - Porta 8081)

```bash
export PORTA_HOST=8081
docker compose -p maya-staging up -d --build
```

---

## Scripts de Automação

Seguindo os requisitos de Sistemas Operacionais, foram desenvolvidos scripts para automatizar tarefas críticas:

| Validação | Resultado | Descrição Técnica |
|-----------|:---------:|:-----------------:|
| `setup.sh` | Provisionamento | nstala o runtime do Docker e automatiza permissões de grupo. |
| `deploy.sh` | Orquestração | Gerencia o build multi-stage e o namespace do projeto de produção. |
| `monitor.sh` | Saúde | Coleta métricas de CPU, Memória e status dos containers em tempo real. |
| `backup.sh` | Segurança | Realiza o dump estruturado (pg_dump) para persistência externa. |

---

## Resultados de Validação (QA)

A infraestrutura foi submetida a testes de estresse e persistência em 11/05/2026 com sucesso:

| Teste | ISO 25010 | Evidência | Resultado |
|-------|:---------:|:---------:|:---------:|
| Persistência de Dados | Confiabilidade | ✅ PASS |
| Conectividade | Interoperabilidade | ✅ OK | 
| Isolamento | Segurança | ✅ OK |
| Eficiência | Performance | ✅ Ativo |
| Recuperabilidade | Segurança | ✅ OK |

---

## Estratégia Cloud Native Aplicada

- **Multi-stage Build:** O Dockerfile utiliza uma etapa de build (Maven) e outra de runtime (JRE Alpine) para reduzir o tamanho da imagem final e aumentar a segurança.

- **Persistência (Volumes):** Utilização de volumes nomeados para garantir que os prontuários e agendamentos não sejam perdidos.

- **Rede Privada:** API e Banco comunicam-se via DNS interno do Docker, protegendo a camada de dados.

- **Integração de Build:** Pipeline de compilação Maven totalmente integrado ao Docker, gerando artefatos executáveis e prontos para produção.

---

## 📚 Documentação Técnica

| Documento | Descrição |
|:----------|:----------|
| [Relatório de Validação](./RELATORIO_TECNICO.md) | Evidências de testes, conectividade, persistência e prints do terminal. |

---
