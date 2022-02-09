### Alura Challenge Back-end 2021
O objetivo é construir uma API REST para o controle financeiro de usuários.
O desafio consiste em etapas de nível básico, intermediário, avançado e deploy em um ambiente cloud!

# 
#### Proposta para evolução do projeto
- [ ] Desenvolver o front-end [Angular].
- [ ] Teste Automatizado [JUnit, Mockito, Hamcrest].
- [ ] Criação do banco de dados com migration [Flyway].

# 
#### Neste projeto foi abordado os seguintes tópicos:
- [x] Operações para gerenciamento de usuários, receitas e despesas utilizando o padrão arquitetural REST (Cadastro, leitura, atualização e remoção) do sistema.
- [x] Controle de versão através do Github.
- [x] Documentação utilizando Swagger e SpringFox.
- [x] Segurança com oauth2 e JWT
- [x] Implantação do sistema na nuvem através do Heroku.

# 
Tecnologias utilizada:
* Java
* Spring
* Mysql [**Dev**] e PostgreSQL [**Produção**]
* Hibernate
* Postman

# 
São necessários os seguintes pré-requisitos para a execução do projeto
- Java 11 ou versões superiores.
- Maven 3.6.3 ou versões superiores.
- Intellj IDEA Community Edition ou sua IDE favorita.

# 
Para executar o projeto no terminal, digite o seguinte comando:

`mvn spring-boot:run `

Após executar o comando acima, basta apenas abrir os seguintes endereços e visualizar a execução do projeto:

`http://localhost:8080/api/v1/receitas`

`http://localhost:8080/api/v1/despesas`

`http://localhost:8080/api/v1/resumos/2030/12`

`http://localhost:8080/api/v1//users`

`http://localhost:8080/api/v1/tokens/revoke`

# 
Documentação

- Disponível no seguinte endereço: [Swagger-ui](https://alura-challenge2021.herokuapp.com/swagger-ui.html "Documentação")

- Postman:  [Collection](https://www.getpostman.com/collections/9e4257aab624cdfe8725 "Documentação")
