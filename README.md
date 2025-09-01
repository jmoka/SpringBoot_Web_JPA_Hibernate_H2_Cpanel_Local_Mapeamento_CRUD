# 🛒 Projeto de E-commerce com Spring Boot

API RESTful para gerenciamento de usuários, produtos, categorias, pedidos e itens de pedido.
Desenvolvida com **Spring Boot**, **JPA/Hibernate** e **MySQL**, seguindo boas práticas de organização, tratamento de exceções e camadas de serviço.

---

## 🚀 Tecnologias Utilizadas

![Java](https://img.shields.io/badge/Java-17-red?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.4-green?logo=springboot)
![Hibernate](https://img.shields.io/badge/Hibernate-ORM-orange?logo=hibernate)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue?logo=mysql)
![MariaDB](https://img.shields.io/badge/MariaDB-Database-blue?logo=mariadb)
![H2](https://img.shields.io/badge/H2-Database-lightgrey)
![Maven](https://img.shields.io/badge/Maven-Build-orange?logo=apachemaven)
![Lombok](https://img.shields.io/badge/Lombok-Library-yellowgreen)

---

## 📦 Dependências Principais

Para utilizar o JPA/Hibernate e os bancos de dados, as seguintes dependências são necessárias no `pom.xml`:

### Spring Data JPA

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

Fornece todas as funcionalidades do JPA/Hibernate, incluindo:

- Mapeamento objeto-relacional (ORM)
- Gerenciamento de entidades
- Repositórios e consultas
- Transações

### MySQL Connector

```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

Driver JDBC para conexão com MySQL:

- Necessário para ambientes `local` e `cpanel`
- Suporte a todas as funcionalidades do MySQL 8+

### MariaDB Driver

```xml
<dependency>
    <groupId>org.mariadb.jdbc</groupId>
    <artifactId>mariadb-java-client</artifactId>
    <version>3.5.5</version>
</dependency>
```

Driver alternativo compatível com MySQL:

- Suporte adicional para MariaDB
- Melhor performance em alguns casos

O H2 Database já está incluído no `spring-boot-starter-test`.

---

## ⚙️ Configurações por Perfil

### Perfil Local (`application-local.properties`)

```properties
# Datasource - Conexão MySQL Local
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco
spring.datasource.username=root
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configurações JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.sql.init.mode=always
```

Explicação das propriedades:
- `spring.datasource.url`: URL de conexão com o banco local, usando porta padrão 3306
- `spring.datasource.username`: Nome do usuário do MySQL
- `spring.datasource.password`: Senha do usuário MySQL
- `spring.datasource.driver-class-name`: Driver JDBC do MySQL
- `spring.jpa.hibernate.ddl-auto=update`: Atualiza o schema automaticamente, mantendo dados existentes
- `spring.jpa.show-sql=true`: Mostra queries SQL no console
- `spring.jpa.open-in-view=true`: Permite carregamento lazy em controllers
- `spring.jpa.properties.hibernate.dialect`: Especifica o dialeto MySQL para o Hibernate
- `spring.sql.init.mode=always`: Sempre executa scripts SQL de inicialização

### Perfil de Testes (`application-test.properties`)

```properties
# Datasource - H2 Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Configurações JPA
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.defer-datasource-initialization=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.ddl-auto=create-drop
spring.sql.init.mode=always
```

Explicação das propriedades:
- `spring.datasource.url`: URL do banco H2 em memória
- `spring.datasource.driver-class-name`: Driver do H2
- `spring.datasource.username`: Usuário padrão do H2 (sa = system admin)
- `spring.datasource.password`: Senha em branco para testes
- `spring.h2.console.enabled`: Habilita console web do H2
- `spring.h2.console.path`: Caminho do console H2
- `spring.jpa.database-platform`: Dialeto do H2
- `spring.jpa.defer-datasource-initialization`: Permite inicialização de dados após criação das tabelas
- `spring.jpa.properties.hibernate.format_sql`: Formata SQL para melhor leitura
- `spring.jpa.hibernate.ddl-auto=create-drop`: Recria o banco a cada execução
- `spring.sql.init.mode=always`: Sempre executa scripts SQL de inicialização

### Perfil cPanel (`application-cpanel.properties`)

```properties
# Datasource - MySQL Remoto
spring.datasource.url=jdbc:mysql://162.251.85.24:3306/teusiaqe_meu_banco?useSSL=false&serverTimezone=UTC
spring.datasource.username=teusiaqe
spring.datasource.password=********
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configurações JPA
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=true
spring.sql.init.mode=always
```

Explicação das propriedades:
- `spring.datasource.url`: URL do banco MySQL remoto no cPanel
  - `162.251.85.24:3306`: Endereço e porta do servidor MySQL
  - `teusiaqe_meu_banco`: Nome do banco de dados
  - `useSSL=false`: Desabilita conexão SSL (útil quando o certificado não é válido)
  - `serverTimezone=UTC`: Define fuso horário do servidor como UTC
- `spring.datasource.username`: Usuário do MySQL no cPanel
- `spring.datasource.password`: Senha do usuário (ocultada por segurança)
- `spring.datasource.driver-class-name`: Driver JDBC do MySQL
- `spring.jpa.properties.hibernate.dialect`: Dialeto MySQL para o Hibernate
- `spring.jpa.hibernate.ddl-auto=update`: Atualiza schema preservando dados existentes
- `spring.jpa.show-sql=true`: Mostra SQL para monitoramento
- `spring.jpa.open-in-view=true`: Permite lazy loading em controllers
- `spring.sql.init.mode=always`: Executa scripts SQL de inicialização

### Configurações Globais (`application.properties`)

```properties
# Configurações JPA comuns
spring.jpa.open-in-view=true
spring.jpa.show-sql=true
```

Estas configurações são aplicadas a todos os perfis:
- `spring.jpa.open-in-view=true`: Habilita carregamento lazy em controllers (mantém a sessão do Hibernate aberta)
- `spring.jpa.show-sql=true`: Habilita log de SQL em todos os ambientes para monitoramento

---

## 📂 Estrutura do Projeto

```
src/main/java/com/jotaempresas/curso/
├── config/              # Configurações e inicialização de dados
├── entity/              # Entidades JPA (modelo de domínio)
│   ├── enums/           # Enums
│   ├── exception/       # Exceções personalizadas
│   └── pk/              # Chaves compostas
├── repositories/        # Repositórios Spring Data JPA
├── resources/           # Controladores REST (API)
└── service/            # Camada de serviços (regras de negócio)
```

---

## 📊 Modelo de Domínio (Mermaid)

```mermaid
erDiagram
    User ||--o{ Order : has
    Order ||--|{ OrderItem : contains
    Order ||--|| Payment : has
    Category ||--o{ Product : has
    Product ||--o{ OrderItem : included_in

    User {
        Long id PK
        String name
        String email
        String phone
        String password
    }

    Order {
        Long id PK
        Instant moment
        int orderStatus
        Long user_id FK
    }

    Payment {
        Long id PK
        Instant moment
        Long order_id FK
    }

    Category {
        Long id PK
        String name
    }

    Product {
        Long id PK
        String name
        String description
        Double price
        String imgUrl
        Long category_id FK
    }

    OrderItem {
        Long order_id PK, FK
        Long product_id PK, FK
        Integer quantity
        Double price
    }
```

---

## ⚙️ Funcionalidades da API

✅ CRUD completo para **Usuários, Categorias, Produtos, Pedidos e Itens de Pedido**  
✅ **Validações e regras de negócio** na camada de serviço  
✅ **Tratamento centralizado de exceções** (`@RestControllerAdvice`)  
✅ **Perfis configuráveis** (`local`, `test`, `cpanel`)  
✅ **Banco H2** para desenvolvimento/testes rápidos  

---

## 🔗 Endpoints Principais

### 👤 Usuários (`/users`)

- `GET /users` → Lista todos
- `GET /users/{id}` → Busca por ID
- `GET /users/email/{email}` → Busca por email
- `POST /users/insert` → Cadastra usuário
- `PUT /users/updateUser/{id}` → Atualiza dados
- `DELETE /users/deletar/{id}` → Remove usuário

### 📦 Produtos (`/products`)

- `GET /products` → Lista todos
- `GET /products/{id}` → Busca por ID
- `DELETE /products/deletar/{id}` → Remove produto

### 🏷️ Categorias (`/categorys`)

- `GET /categorys` → Lista todas
- `GET /categorys/{id}` → Busca por ID
- `DELETE /categorys/deletar/{id}` → Remove categoria

### 🧾 Pedidos (`/orders`)

- `GET /orders` → Lista todos
- `GET /orders/{id}` → Busca por ID
- `DELETE /orders/deletar/{id}` → Remove pedido

### 📑 Itens de Pedido (`/orderItem`)

- `GET /orderItem` → Lista todos
- `GET /orderItem/{id}` → Busca por ID
- `DELETE /orderItem/deletar/{id}` → Remove item

---

## 🛠️ Tratamento de Exceções

| Exceção                       | Status HTTP | Descrição                                 |
| ----------------------------- | ----------- | ----------------------------------------- |
| `ResourceBadRequestException` | `400`       | Requisição inválida (campos obrigatórios) |
| `ResourceConflictException`   | `409`       | Conflito de dados (e.g., email duplicado) |
| `ResourceNotFoundException`   | `404`       | Recurso não encontrado                    |

📌 Todas as respostas de erro seguem o padrão definido em `ExecptionErrorClasse`.

---

## ⚡ Perfis de Configuração

- **`local`** → Conexão com MySQL local
- **`test`** → Banco **H2 em memória** (com console `/h2-console`)
- **`cpanel`** → Banco remoto MySQL (produção/cPanel)

---

## ▶️ Como Executar o Projeto

### Pré-requisitos

- **JDK 17+**
- **Maven**
- Banco de dados configurado (MySQL ou H2)

### Passos

```bash
# Compilar o projeto
mvn clean install

# Executar com perfil local (MySQL)
mvn spring-boot:run -Dspring-boot.run.profiles=local

# Executar com perfil de teste (H2)
mvn spring-boot:run -Dspring-boot.run.profiles=test

# Executar com perfil de produção (cPanel)
mvn spring-boot:run -Dspring-boot.run.profiles=cpanel
```

A aplicação estará disponível em:
👉 `http://localhost:8080`

---

## ✨ Autor

Desenvolvido por **Jota Empresas**  
📧 [Entre em contato](mailto:seuemail@exemplo.com)

---
