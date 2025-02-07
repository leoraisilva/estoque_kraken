# Etapa 1: Construção do artefato com Maven
FROM maven:3.8-openjdk-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src /app/src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/estoque-0.0.1-SNAPSHOT.jar /app/estoque.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app/estoque.jar"]
