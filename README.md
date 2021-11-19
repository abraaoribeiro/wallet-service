# Wallet Service
## Sobre o projeto

Wallet Service é um projeto baseado na arquitetura de microservices, onde seu principal propósito é o gerenciamento de transferências e sua integridade.

## Tecnologias utilizadas e Ferramentas

- Intellij
- Java 11
- Spring boot
- Docker
- Banco de dados Mysql
- Swagger

## Este repositório possui:
- [x] Service Discovery utilizando o Spring Cloud Netflix Eureka. 📂 eureka-server;

- [x] Serviço de externalização de variáveis utilizando o Spring Cloud Config Server. 📂 config-server;
As configurações internalizadas na pasta config-server-files

- [x] Intelligent Routing ("API Gateway") utilizando o Spring Zuul. 📂 zuul;

- [ ] Serviço de autenticação utilizando o Keylock  📂 auth;

- [x] Mensageria utilizando 📂 Rabbitmq

- [x] Microsserviços: 📂 account-api; 📂 payment-api;

### Desenho Arquitetural

![](https://github.com/abraaoribeiro/wallet-service/blob/master/assets/arquitetura.png)

### O que este repositório possui além dos microsserviços ?

- [x] Teste de unidade por camada Controller, Repository e Service
- [x] Comunicação assíncrona utilizando Rabbitmq
- [x] Documentação swagger


### Requisitos Funcionais

- [x] Criar conta de usuário

- [x] Realiza Transferência, Saque, Pagamento de Conta e Depósito

- [x] Lista transações


### Execução do projeto com script suporte para Linux, Mac e Windows WSL

Obs: No script contém apenas comandos para realizar o build dos microserviços e
buildar/subir os dockers

1 . Na raiz do projeto execute o comando abaixo para dar permissão ao arquivo.

```sh
chmod +x start.sh
```
2. Agora execute o arquivo start.sh com o seguinte comando :

```sh
./start.sh ou sh start.sh
```

3. Após isso é só aguardar tudo subir.

## Facilitadores para realização de teste 

1 . Para poupar tempo foi criado inserts para adicionar Bancos e Contas automaticamente,
porém caso não insira automaticamente os dados, você pode executá -los manualmente. O script sql está no arquivo
data.sql, na pasta resources.

` Configuração do banco de dados` :

- Datasource: `wallet`
- username: `root`
- password: `root`

[Documentação Postman](https://github.com/abraaoribeiro/wallet-service/blob/master/assets/Wallet-Service.postman_collection.json)

## Links dos serviços utilizados

[Swagger Account API](http://localhost:5555/account/swagger-ui.html)

[Rabbitmq](http://localhost:15672/#/) (usuário: admin , senha : admin)

[API Gateway - routes](http://localhost:5555/actuator/routes)

[Eureka - apps](http://localhost:8761/eureka/apps)

