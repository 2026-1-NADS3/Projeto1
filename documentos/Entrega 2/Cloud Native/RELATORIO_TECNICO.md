# Relatório de Validação Técnica — Cloud Native

> **Projeto:** Maya Fisioterapia/RPG — Backend & Infraestrutura
>
> **Aluno responsável:** Luiz Felipe da Silva Lima
>
>**Escopo:** Automação Linux e Containerização
>
> **Validação técnica:** 11/05/2026 em ambiente Linux Ubuntu Server (Docker v26.x / Compose v2.x)
>
> **Evidências visuais:** [`Imagens/cloud-native/`](../../../Imagens/cloud-native/)

Este documento detalha os testes realizados para validar a resiliência, persistência e automação da infraestrutura do Projeto Maya.

---

## Orquestração e Isolamento de Ambientes

**Objetivo:** Validar a capacidade da infraestrutura de rodar instâncias isoladas (Multi-tenancy).

Utilizando o parâmetro `-p` (project), isolamos o tráfego e os dados.

- **Produção:** Porta 8080 (`producao-maya-api-rest-1`)
- **Staging:** Porta 8081 (`maya-staging-maya-api-rest-1`)
* **Evidência:** `docker ps` mostrando múltiplos projetos ativos.

![Status dos Containers](../../../imagens/cloud-native/02-status.png)

---

## Conectividade de Rede (Interoperabilidade)
**Objetivo:** Provar que a rede isolada `producao_maya-network` permite a comunicação segura via DNS interno entre os serviços do ecossistema.

Como os containers estão em uma rede do tipo `bridge` privada, o banco de dados e a API conseguem se comunicar sem exposição desnecessária ao mundo externo.

* **Comando:** `docker exec producao-maya-db-server-1 ping producao-maya-api-rest-1 -c 3`
* **Resultado esperado:** 0% de perda de pacotes e resolução de nome automática.
* **Evidência:**

![Teste de Ping](../../../imagens/cloud-native/03-ping.png)

---

## Persistência de Dados (Confiabilidade)

**Objetivo:** Validar se as informações clínicas dos pacientes sobrevivem a falhas ou reinicializações do servidor através de Volumes Persistentes.

* **Teste realizado:** Criação de tabela `valida_entrega`, execução de `docker compose down` e posterior consulta após novo deploy.

* **Comando de validação:** 

```bash
docker exec -it container-maya-db psql -U admin_maya -d maya_db_production -c "SELECT * FROM valida_entrega;"
```

> **Nota:** Nome do container varia conforme o projeto (ex: producao-maya-db-server-1)


* **Resultado:** O dado `INFRA_OK` foi retornado com sucesso após o restart, comprovando que o volume Docker está mapeando os dados corretamente para o armazenamento físico do host.

* **Evidência:**

![Persistência de Dados](../../../imagens/cloud-native/05-persistencia.png)

---

## Automação e Monitoramento
**Objetivo:** Demonstrar o uso de Shell Scripting para automação de tarefas de administração de sistemas (SysAdmin).

### 1. Deploy Automatizado

* **Comando:** `./scripts/deploy.sh`
* **Descrição:** Script que automatiza o ciclo de vida: limpa o ambiente, realiza o build multi-stage da API e sobe os serviços.

![Execução do Deploy](../../../imagens/cloud-native/01-deploy.png)

### 2. Backup Estruturado
* **Comando:** `./scripts/backup.sh`
* **Verificação:** `ls -lh ~/backups/`
* **Evidência:** Print do comando executado e do arquivo `.sql` gerado na pasta `~/backups`.

![Backup](../../../imagens/cloud-native/05-backup.png)

### 3. Monitoramento de Recursos
* **Comando:** `./scripts/monitor.sh`
* **Evidência:** Print da tabela de consumo de CPU/RAM em tempo real.

![Monitorização de Hardware](../../../imagens/cloud-native/04-monitor.png)

---

## 5. Validação de Runtime da API (Sucesso de Integração)

**Objetivo:** Provar que a API é um serviço funcional e não apenas um container vazio.

### Passo 1: Logs do Spring Boot

* **Comandos:** `docker logs producao-maya-api-rest-1` ou  `docker logs maya-staging-maya-api-rest-1`
* **Resultado:** Sucesso no bootstrap do Spring Boot e ativação da JVM na porta 8080.
* **Evidência:**

![Execução do Log](../../../imagens/cloud-native/06-api-log.png)

### Passo 2: Validação via Curl (Porta 8080)

* **Comando:** `curl http://localhost:8080`
* **Resultado:** Resposta direta da aplicação confirmando a validação da infraestrutura.
* **Evidência:**

![Validação da API](../../../imagens/cloud-native/07-api-check.png)

---

## Mapeamento ISO/IEC 25010

| Característica | Implementação Prática |
|:---:|:---|
| **Confiabilidade** | Persistência via Volumes Docker e Recuperabilidade via Backup automático. |
| **Escalabilidade** | Arquitetura multi-ambiente permitindo deploys paralelos. |
| **Portabilidade** | Uso de Dockerfile multi-stage com JRE Alpine (leve e seguro). |
| **Segurança** | Rede interna isolada (maya-network) protegendo o Banco de Dados. |

---
