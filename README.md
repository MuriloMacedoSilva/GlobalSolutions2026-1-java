# 🌱 Space Agro — API

**Plataforma inteligente de gestão agrícola para produtores rurais**, permitindo o cadastro e monitoramento de talhões com dados de localização, cultura e área cultivada.

---

## 🧠 Contexto do Projeto

O **Space Agro** é um ecossistema completo voltado ao agronegócio, composto por:

- **API REST** (este repositório) — Backend em Java com Spring Boot, conectado ao banco Oracle da FIAP
- **Frontend Web** — Aplicação em React + Next.js para interface com o usuário
- **Aplicação Mobile** — Aplicativo em React Native para acesso em campo

A API expõe endpoints para gerenciamento de **produtores** (cadastro e autenticação) e **talhões** (CRUD completo com geolocalização), servindo tanto o frontend web quanto o mobile.

---

## 📦 Stack Tecnológica

| Tecnologia | Versão | Finalidade |
|---|---|---|
| Java | 21 | Linguagem principal |
| Spring Boot | 4.0.6 | Framework web |
| Spring Data JPA | — | ORM e persistência |
| Hibernate | — | Mapeamento objeto-relacional |
| Oracle Database 21c | — | Banco de dados (FIAP Oracle) |
| Maven | — | Gerenciamento de dependências |
| Lombok | — | Redução de boilerplate |
| SpringDoc OpenAPI (Swagger) | 3.0.2 | Documentação interativa |
| Oracle JDBC (ojdbc11) | — | Driver de conexão Oracle |

---

## 🏗️ Arquitetura do Projeto

```
apiGlobalSolutions/
├── api/
│   ├── src/main/java/com/SpaceAgro/api/
│   │   ├── ApiApplication.java              ← Entry point Spring Boot
│   │   ├── config/
│   │   │   └── CorsConfig.java              ← Configuração global de CORS
│   │   ├── controller/
│   │   │   ├── ProdutorController.java      ← Endpoints de produtor
│   │   │   └── TalhaoController.java        ← Endpoints de talhão
│   │   ├── dto/
│   │   │   └── LoginRequest.java            ← DTO para requisição de login
│   │   ├── model/
│   │   │   ├── Produtor.java                ← Entidade TB_PRODUTOR
│   │   │   └── Talhao.java                  ← Entidade TB_TALHAO
│   │   ├── repository/
│   │   │   ├── ProdutorRepository.java      ← Acesso a dados de produtor
│   │   │   └── TalhaoRepository.java        ← Acesso a dados de talhão
│   │   └── service/
│   │       ├── ProdutorService.java         ← Lógica de negócio de produtor
│   │       └── TalhaoService.java           ← Lógica de negócio de talhão
│   ├── src/main/resources/
│   │   └── application.properties           ← Configurações do ambiente
│   ├── pom.xml                              ← Dependências Maven
│   └── HELP.md
```

### 📐 Padrão Arquitetural — MVC (Model-View-Controller)

O projeto segue o padrão **MVC** com a seguinte camada:

1. **Controller** — Expõe os endpoints REST e orquestra as requisições HTTP
2. **Service** — Contém as regras de negócio e validações
3. **Repository** — Camada de persistência com Spring Data JPA
4. **Model** — Entidades JPA que mapeiam as tabelas do banco Oracle

### 🌐 Camada de Configuração

- **CorsConfig** — Permite requisições de qualquer origem (CORS liberado para desenvolvimento)
- **application.properties** — Configurações de conexão Oracle, dialeto Hibernate e porta do servidor

---

## 🗄️ Modelo de Dados

### TB_PRODUTOR

| Coluna | Tipo | Restrição | Descrição |
|---|---|---|---|
| ID_PRODUTOR | NUMBER | PK, Auto Increment | Identificador único |
| NOME | VARCHAR2 | NOT NULL | Nome do produtor |
| EMAIL | VARCHAR2 | NOT NULL | Email para login |
| SENHA | VARCHAR2 | NOT NULL | Senha de acesso |

### TB_TALHAO

| Coluna | Tipo | Restrição | Descrição |
|---|---|---|---|
| ID_TALHAO | NUMBER | PK, Auto Increment | Identificador único |
| NOME_TALHAO | VARCHAR2 | — | Nome do talhão |
| CULTURA | VARCHAR2 | — | Tipo de cultura plantada |
| AREA_HECTARES | NUMBER | — | Área em hectares |
| LATITUDE | NUMBER | — | Latitude geográfica |
| LONGITUDE | NUMBER | — | Longitude geográfica |
| ID_PRODUTOR | NUMBER | — | FK para TB_PRODUTOR |

---

## 🔌 Endpoints da API

### 👤 Produtores

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/produtores` | Lista todos os produtores |
| `GET` | `/produtores/{id}` | Busca produtor por ID |
| `POST` | `/salvarProdutor` | Cadastra um novo produtor |
| `POST` | `/login` | Autentica produtor (email + senha) |

**`POST /login` — Request Body:**
```json
{
  "email": "joao@email.com",
  "senha": "123456"
}
```

**`POST /login` — Response 200 (sucesso):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "123456"
}
```

**`POST /login` — Response 401 (falha):**
```
Email ou senha inválidos
```

### 🌾 Talhões

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/talhoes` | Lista todos os talhões |
| `GET` | `/talhoes/{id}` | Busca talhão por ID |
| `GET` | `/talhoes/produtor/{idProdutor}` | Lista talhões de um produtor |
| `POST` | `/talhoes` | Cadastra um novo talhão |
| `PUT` | `/talhoes/{id}` | Atualiza dados de um talhão |
| `DELETE` | `/talhoes/{id}` | Remove um talhão |

**`POST /talhoes` — Request Body:**
```json
{
  "nomeTalhao": "Talhão Norte",
  "cultura": "Soja",
  "areaHectares": 120.5,
  "latitude": -23.5505,
  "longitude": -46.6333,
  "idProdutor": 1
}
```

**`PUT /talhoes/{id}` — Request Body:**
```json
{
  "nomeTalhao": "Talhão Sul",
  "cultura": "Milho",
  "areaHectares": 85.0,
  "latitude": -23.5605,
  "longitude": -46.6433,
  "idProdutor": 1
}
```

---

## 🚀 Como Rodar

### Pré-requisitos

- **Java 21** (JDK)
- **Maven** 3.9+
- Acesso ao banco Oracle da FIAP (VPN FIAP, se necessário)

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/apiGlobalSolutions.git
cd apiGlobalSolutions/api
```

### 2. Configure o banco de dados

Edite `src/main/resources/application.properties` com suas credenciais Oracle:

```properties
spring.datasource.url=jdbc:oracle:thin:@//oracle.fiap.com.br:1521/orcl
spring.datasource.username=SEU_RM
spring.datasource.password=SUA_SENHA
```

### 3. Execute a aplicação

```bash
./mvnw spring-boot:run
```

A API será iniciada em: **http://localhost:5000**

### 4. (Opcional) Build do JAR

```bash
./mvnw clean package -DskipTests
java -jar target/api-0.0.1-SNAPSHOT.jar
```

---

## 📖 Documentação Interativa (Swagger)

Com a aplicação rodando, acesse:

- **Swagger UI:** http://localhost:5000/swagger-ui/index.html
- **OpenAPI JSON:** http://localhost:5000/v3/api-docs

---

## 🧪 Testes

```bash
./mvnw test
```

---

## 👨‍💻 Integrantes do Grupo

| Nome | RM |
|---|---|
| Murilo | RM566462 |
| — | — |
| — | — |
| — | — |
| — | — |

> *Projeto desenvolvido para a Global Solutions da FIAP (2025/2026).*

---

## 📄 Licença

Este projeto é de uso acadêmico — FIAP.
# GlobalSolutions2026-1-java
