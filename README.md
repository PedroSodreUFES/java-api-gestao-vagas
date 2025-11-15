# Api Java de gestão de vagas
## Tecnologias
+ PostgreSQL - Banco de dados SQL.
+ H2 - Banco de dados In-Memory
+ Spring Actuator - Monitoramento.
+ Spring Boot - Framework.
+ Spring Data - Definição de tabelas.
+ Spring Devtools - Quick reload.
+ Spring Jpa - Repositórios.(ORM)
+ Spring Security - Autenticação.
+ Spring Security Test - Testes.
+ Spring Test - Testes.
+ Spring Validation - Validação de Dados
+ Java JWT - Autenticação.
+ Lombok - Build data.
+ Swagger - Documentação.
+ Grafana - Monitoramento.
+ Prometheus - Monitoramento.
+ JUNIT - Testes.
+ Jacoco - Qualidade de código.
+ Sonar - Qualidade de código.
## Como rodar a aplicação
### Substituir em src/resources/application.properties as seguintes linhas
spring.datasource.url=jdbc:postgresql://localhost:5432/gestao_vagas<br />
spring.datasource.username=admin<br/>
spring.datasource.password=admin
### Rodar no terminal
```bash
docker-compose up -d
mvn spring:boot:run
```
## Créditos
> Pedro Sodré Malini, 9 de Novembro de 2025