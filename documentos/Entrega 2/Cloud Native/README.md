# Relatório — Containerização e Deploy Cloud Native

**Projeto:** Maya RPG (Gestão de Fisioterapia)  
**Disciplina:** Sistemas Operacionais e Cloud Native  
**Responsável pela Infraestrutura:** Luiz Felipe da Silva Lima  

---

## 1. Evolução: Da Automação para a Containerização

Nesta segunda etapa, o projeto **Maya RPG** evoluiu da automação via Shell Script para uma arquitetura **Cloud Native**. A containerização empacota a API e o banco de dados em unidades isoladas, garantindo que o sistema de agendamento de sessões de fisioterapia seja resiliente e escalável.

### Vantagens da Containerização no Projeto:
- **Portabilidade:** O ambiente de desenvolvimento na VM Ubuntu é rigorosamente idêntico ao ambiente de produção (AWS/Azure).
- **Isolamento:** Cada serviço possui seus próprios recursos e rede. Uma falha crítica na API não compromete a integridade dos dados no PostgreSQL.
- **Reprodutibilidade:** A infraestrutura é descrita como código (`Dockerfile` e `docker-compose.yml`), permitindo o provisionamento total com apenas um comando.
- **Escalabilidade:** Novas instâncias da API podem ser criadas rapidamente a partir da mesma imagem base.

---

## 2. Comparativo: Ambiente Tradicional vs. Containerizado

| Aspecto | Tradicional (Entrega 1) | Containerizado (Entrega 2) |
|---|---|---|
| **Instalação** | Manual (OpenJDK, Postgres, etc) | Imagem Docker Reutilizável |
| **Configuração** | Variáveis de ambiente no `.bashrc` | Variáveis dinâmicas no `docker-compose` |
| **Isolamento** | Processos compartilham o mesmo SO | Serviços em containers isolados |
| **Persistência** | Backup manual via script `.sh` | Gerenciada por Volumes Nomeados |
| **Setup** | Horas de configuração manual | Segundos via `docker compose up` |

---

## 3. Estrutura de Arquivos da Entrega

```
text
mayarpg/ (Raiz)
├── pom.xml                → Manual de build do Maven (Essencial)
└── documentos/
    └── Entrega 2/
        └── Cloud Native/
            ├── database/
            │   └── Dockerfile  → Configuração do PostgreSQL 15
            ├── Dockerfile      → Build Multi-stage da API Java
            ├── docker-compose.yml → Orquestrador de Infraestrutura
            └── README.md       → Este Relatório Técnico
```

---

## 4. Estratégia de Infraestrutura e Persistência

## 4.1 Multi-Stage Build (Otimização)

Utilizamos um build em dois estágios no Dockerfile da API. Isso separa o ambiente de compilação (Maven) do ambiente de execução (JRE), resultando em uma imagem final leve e segura, reduzindo a superfície de ataque conforme a norma ISO 25010.

4.2 Estratégia de Volumes (Persistência)
Por padrão, containers são efêmeros. Para garantir que os prontuários dos pacientes sejam preservados, implementamos um Volume Nomeado mapeado para o diretório de dados do PostgreSQL:

## 4. Estratégia de Infraestrutura e Persistência

## 4.1 Multi-Stage Build (Otimização)

Utilizamos um build em dois estágios no Dockerfile da API. Isso separa o ambiente de compilação (Maven) do ambiente de execução (JRE), resultando em uma imagem final leve e segura, reduzindo a superfície de ataque conforme a norma ISO 25010.

## 4.2 Estratégia de Volumes (Persistência)

Por padrão, containers são efêmeros. Para garantir que os prontuários dos pacientes sejam preservados, implementamos um Volume Nomeado mapeado para o diretório de dados do PostgreSQL:

```
services:
  maya-db-server:
    volumes:
      - maya_data_persistence:/var/lib/postgresql/data

volumes:
  maya_data_persistence:
    name: volume-persistente-maya
```

## 4.3 Orquestração de Rede e Segurança

Implementamos a maya-network do tipo bridge. A API comunica-se com o banco através do hostname interno, mantendo o tráfego de dados do PostgreSQL isolado e inacessível por acessos externos não autorizados.

---

## 5. Como Rodar o Ambiente

1. Certifique-se de estar na pasta da entrega:

```
cd "documentos/Entrega 2/Cloud Native"
```

2. Provisione a infraestrutura completa:

```
cd "documentos/Entrega 2/Cloud Native"
```

3. Valide o status dos serviços:

```
docker ps
```

---

## 6. Qualidade de Software (ISO 25010)

O Maya RPG foi containerizado considerando as seguintes características:

```
Adequação Funcional: Ambiente pronto para hospedar os serviços de agendamento.

Confiabilidade: Recuperação automática de falhas e persistência via volumes.

Eficiência de Desempenho: Uso de imagens base Alpine para menor consumo de memória e CPU.

Portabilidade: Capacidade de deploy em qualquer ecossistema Cloud Native sem reconfiguração.
```

---
