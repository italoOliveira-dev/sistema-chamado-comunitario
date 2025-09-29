# 📘 Projeto de Integração de Sistemas com Microsserviços

### 🌍 Relação com o ODS 11 - Cidades e Comunidades Sustentáveis

#### 🎯 Alinhamento com os Objetivos de Desenvolvimento Sustentável

O projeto foi desenvolvido com base nos princípios do **ODS 11 (Cidades e Comunidades Sustentáveis)** da ONU, que busca tornar as cidades e os assentamentos humanos inclusivos, seguros, resilientes e sustentáveis.

## 🏙️ Como o Sistema Apoia o ODS 11

| Funcionalidade | Benefício | Impacto no ODS 11 |
|----------------|-----------|-------------------|
| **📢 Microserviço de Chamados Comunitários** | Permite registro de problemas urbanos | Fortalece a participação social e gestão democrática |
| **⚡ API Gateway Centralizado** | Otimiza gestão de informações urbanas | Torna a gestão pública mais eficiente e transparente |
| **🔒 Serviço de Autenticação** | Garante acesso seguro e controlado | Promove transparência e confiança nos processos |
| **👥 Microserviço de Usuários** | Democratiza o acesso à plataforma | Garante inclusão digital e equidade no acesso |

## 🌱 Contribuições para a Sustentabilidade Urbana

### 🎯 Metas do ODS 11 Atendidas

- ✅ **11.3** - Urbanização inclusiva e sustentável
- ✅ **11.6** - Redução do impacto ambiental negativo
- ✅ **11.7** - Acesso universal a espaços públicos seguros

### 📊 Impactos Mensuráveis

```bash
🛣️  Buracos em vias públicas
💡 Iluminação pública defeituosa
🗑️  Coleta de lixo irregular  
🌊 Problemas de drenagem urbana
🌳 Manutenção de áreas verdes
```

### 🎯 Problemas Urbanos que Podem ser Reportados

- 🛣️ **Infraestrutura Viária**: Buracos em vias públicas
- 💡 **Iluminação Pública**: Postes defeituosos ou apagados
- 🗑️ **Limpeza Urbana**: Coleta de lixo irregular
- 🌊 **Drenagem**: Problemas de enchentes e alagamentos
- 🌳 **Meio Ambiente**: Manutenção de áreas verdes
- ♿ **Acessibilidade**: Calçadas irregulares

## 🎯 Objetivo do Trabalho

Implementar uma arquitetura de microsserviços utilizando **Spring Boot**, **Spring Cloud Netflix Eureka**, **Spring Cloud Gateway** e **Docker**, demonstrando conceitos de integração, comunicação entre serviços e escalabilidade em sistemas distribuídos.

# 🚀 Executando o Projeto

## 📋 Pré-requisitos

- **Java 21**
- **Gradle** (usando `gradlew` do projeto)
- **Docker** e **Docker Compose**

## ⚠️ Verificações Importantes

**Portas ocupadas:** Verifique se as portas 27017, 3306, 8080, 8081, 8082, 8083 e 8761 estão livres

## ⚡ Execução

```bash
# Subir todos os serviços entrando na diretório sistema-chamado-comunitário
docker-compose up -d
```

## 🌍 Contexto e Justificativa

**Alinhado com o ODS 11 - Cidades e Comunidades Sustentáveis**, este projeto representa uma solução tecnológica para promover:

- 🏙️ Gestão urbana participativa
- 📢 Engajamento comunitário
- ⚡ Eficiência na resolução de problemas urbanos
- 🔒 Transparência e segurança nos processos

## 🛠️ Arquitetura da Solução

### 🔧 Microsserviços Principais

#### 👥 **Serviço de Usuário** (`servico-usuario-api`)
**Responsabilidade**: Cadastro e gerenciamento de usuários

**Funcionalidades**:
- ✅ Criar, listar, atualizar e excluir usuários
- 👨‍💼 Gerenciamento de perfis comunitários
- 🌐 Democratização do acesso à plataforma

#### 🔐 **Serviço de Autenticação** (`servico-autenticacao`)
**Responsabilidade**: Login e emissão de tokens de autenticação

**Funcionalidades**:
- 🔑 Autenticação de usuários cadastrados
- 🛡️ Geração e validação de tokens JWT
- 📊 Garante segurança e transparência nos acessos

#### 📋 **Serviço de Chamado Comunitário** (`servico-chamado-comunitario-api`)
**Responsabilidade**: Abertura e acompanhamento de chamados

**Funcionalidades**:
- 🏘️ Registro de chamados por usuários autenticados
- 📈 Listagem e acompanhamento de ocorrências
- 🎯 Foco em problemas urbanos (iluminação, limpeza, infraestrutura)

#### 🌐 **API Gateway** (`api-gateway`)
**Responsabilidade**: Porta de entrada única do sistema

**Funcionalidades**:
- 🚪 Roteamento inteligente de requisições
- 🔍 Integração com Eureka Server
- ⚡ Centralização do acesso ao sistema

#### 🔍 **Eureka Server**
**Responsabilidade**: Serviço de descoberta de microsserviços

**Funcionalidades**:
- 📍 Registro dinâmico de serviços
- 🔄 Descoberta automática de instâncias
- ⚖️ Balanceamento de carga client-side

## 🏗️ Diagrama de Arquitetura

```bash
graph TB
    Client[Cliente Frontend] --> Gateway[API Gateway]
    
    Gateway --> Eureka[Eureka Server]
    
    Gateway --> Auth[Serviço de Autenticação]
    Gateway --> User[Serviço de Usuário]
    Gateway --> Chamado[Serviço de Chamado Comunitário]
    
    Auth --> DB1[(Database Auth)]
    User --> DB2[(Database Users)]
    Chamado --> DB3[(Database Chamados)]
   
```

## 📊 Fluxo de Funcionamento

1. **🏘️ Identificação** - Cidadão identifica problema urbano
2. **🔐 Autenticação** - Login via Serviço de Autenticação
3. **📝 Registro** - Chamado registrado via Serviço de Chamados
4. **🌐 Roteamento** - API Gateway gerencia a comunicação
5. **🔍 Descoberta** - Eureka Server localiza serviços
6. **📈 Gestão** - Gestor municipal acompanha e prioriza
7. **✅ Resolução** - Problema resolvido, comunidade beneficiada

## 🚀 Stack Tecnológica

| Camada | Tecnologias |
|--------|-------------|
| **Backend** | Spring Boot, Spring Cloud, JPA |
| **Service Discovery** | Eureka Server |
| **API Gateway** | Spring Cloud Gateway |
| **Autenticação** | JWT, Spring Security |
| **Containerização** | Docker |
| **Banco de Dados** | MySQL |

## 💡 Impacto Social

- ✅ **Transparência** na gestão urbana
- ⏱️ **Agilidade** na resolução de problemas
- 👥 **Protagonismo** comunitário
- 🌱 **Cidades sustentáveis**

---

## 🔄 Fluxo de Integração entre Serviços

### 1. **Autenticação do Usuário**
```
Cliente → Serviço de Autenticação (8082)
         ↓
POST /api/auth/login
         ↓
Retorna Token JWT
```

### 2. **Operações com Usuários**
```
Cliente → Serviço de Usuário (8081)
         ↓
GET/POST/PUT/DELETE /api/usuarios/*
         ↓
Gerencia dados dos usuários
```

### 3. **Gestão de Chamados**
```
Cliente → Serviço de Chamados (8083)
         ↓
POST /api/chamados
         ↓
Cria e gerencia ocorrências comunitárias
```

# 📚 Documentação das APIs

## 🏗️ Visão Geral das APIs
Documentação completa dos três microsserviços principais do sistema de gestão comunitária.

---

## 🔐 Serviço de Autenticação

### 🌐 Configuração do Servidor
**URL Base:** `http://localhost:8082`

### 🔑 Endpoints Disponíveis

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/auth/login` | Autenticação de usuários |
| `POST` | `/api/auth/refresh-token` | Renovação do token de acesso |

### 📱 Acesso à Documentação Interativa

http://localhost:8082/swagger-ui.html

---

## 👥 Serviço de Usuário

### 🌐 Configuração do Servidor
**URL Base:** `http://localhost:8081`

### 👤 Endpoints Disponíveis

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `GET` | `/api/usuarios/{id}` | Obter usuário por ID |
| `PUT` | `/api/usuarios/{id}` | Atualizar dados do usuário |
| `DELETE` | `/api/usuarios/{id}` | Deletar usuário por ID |
| `GET` | `/api/usuarios` | Listar todos os usuários |
| `POST` | `/api/usuarios` | Salvar novo usuário |
| `GET` | `/api/usuarios/buscarEmail/{email}` | Obter usuário por Email |

### 📱 Acesso à Documentação Interativa

http://localhost:8081/swagger-ui.html

---

## 📋 Serviço de Chamados Comunitários

### 🌐 Configuração do Servidor
**URL Base:** `http://localhost:8083`

### 🏘️ Endpoints Disponíveis

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/chamados` | Criar um novo chamado |
| `GET` | `/api/chamados/{usuarioId}` | Listar chamados por usuário |

### 📱 Acesso à Documentação Interativa

http://localhost:8083/swagger-ui.html

---


