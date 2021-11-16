#!/bin/bash
mvn clean package -f ./eureka-server/pom.xml & \
mvn clean package -f ./account/pom.xml & \
mvn clean package -f ./client/pom.xml  & \
mvn clean package -f ./payment/pom.xml  & \
mvn clean package -f ./gateway/pom.xml

wait

docker-compose build
docker-compose up