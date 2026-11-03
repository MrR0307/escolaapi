# Escola API

## Descrição

API REST desenvolvida em Java com Spring Boot.
Permite gerir estudantes através de operações CRUD.

## Tecnologias

- Java 26
- Spring Boot 4
- Maven
- Spring Data JPA
- Hibernate
- MySQL 8
- REST API
- JSON

## Base de dados

Nome: escola_api

Tabela: estudante

Campos:
- id
- nome
- apelido
- curso
- idade

## Executar o projecto

1. Instalar Java.
2. Instalar Maven.
3. Instalar MySQL.
4. Criar a base de dados escola_api.
5. Configurar as credenciais do MySQL.
6. Executar:

mvn spring-boot:run

## Endpoints

| Método | Endpoint | Operação |
| POST | /api/estudantes | Criar estudante |
| GET | /api/estudantes | Listar estudantes |
| GET | /api/estudantes/{id} | Procurar estudante |
| PUT | /api/estudantes/{id} | Actualizar estudante |
| DELETE | /api/estudantes/{id} | Eliminar estudante |

## Health Check

GET /actuator/health

## Porta

8080