# cadastro project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/cadastro-0.0.1-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Provided examples

### RESTEasy JAX-RS example

REST is easy peasy with this Hello World RESTEasy resource.

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)

# Iniciando o banco de dados

1. Acessar `ifood/docker_database`
2. Executar comando shell `./start.sh`
3. Depois que container subir, acessar e configurar banco no postgres
4. Criar novo servidor de banco no pgadmin: http://localhost:5050
5. Login: user@domain.com e senha: 112358
6. Criar banco com nome`ifood`
7. Criar banco com nome `keycloak`

# Iniciando os outros containers

É necessário criar o banco do `keycloak` antes de iniciar o próprio keycloak e o Jaeger.

1. Acessar ifood/docker_outros
2. Executar comando shell `./start.sh`

# Configurando o keycloak

1. Logar no console do [keycloak](http://localhost:8180/auth/)
2. Login e senha `admin` `admin`
3. Add realm nome `ifood`
4. Criar Clients
5. Client-id: `front-web-cadastro`
6. Valid Redirect URIs: *
7. Web Origins: *
8. Criar um `users` - add user
9. Username: `proprietario1` e email `prop@prop.com`
10. Email Verified: ON
11. Credentials: password `senha123` e Temporary `OFF`
12. Criar a Roles: add role `proprietario`
13. Colocar o usuário `proprietario1` na role
14. Adicionar ao Client `front-web-cadastro` Mappers `realm roles`
15. Editar o Mapper `realm roles` em `front-web-cadastro`
16. Token Claim Name: `groups`

## Atualizar a chave public no projeto

1. Acessar a url http://localhost:8180/auth/realms/ifood
2. Pegar `public_key`
3. No `application.properties` do projeto, adicionar a chave no campo ``

# Acessos

Jaeger: http://localhost:16686/search
pgAdmin: http://localhost:5050
KeyCloak: http://localhost:8180/auth/