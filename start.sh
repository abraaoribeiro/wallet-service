#!/bin/bash
echo "========= Subindo o banco de dados =============="
docker-compose up -d wallet-mysql

echo "========= Iniciando mvn build Config server e Eureka server =============="

wait
mvn clean package -f ./config-server/pom.xml -DskipTests & \
mvn clean package -f ./eureka-server/pom.xml -DskipTests &

echo "========= Fim mvn build Config server e Eureka server =============="
wait

echo "========= Subindo os containers Config server, Eureka server  =============="
docker-compose build  microservice-config-server microservice-eureka-server
docker-compose up -d microservice-config-server microservice-eureka-server
echo "========= Fim Run docker Config server, Eureka server =============="
echo "========= Aguardando 5 segundos para Config server e Eureka server subir =============="
sleep 5

echo "========= Iniciando mvn build account-api, client-api, payment-api e gateway =============="
mvn clean package -f ./account-api/pom.xml & \
mvn clean package -f ./payment-api/pom.xml & \
mvn clean package -f ./gateway/pom.xml -DskipTests

echo "========= Fim mvn build account-api, client-api, payment-api e gateway =============="

wait
echo "========= Subindo os containers account-api, client-api, payment-api e gateway =============="
docker-compose build microservice-account-api microservice-payment-api microservice-gateway
docker-compose up microservice-account-api microservice-payment-api microservice-gateway