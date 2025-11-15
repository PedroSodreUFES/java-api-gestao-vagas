FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y
COPY . .

RUN apt-get install maven -y
RUN mvn clean install -DskipTests

# Imagem oficial atual do OpenJDK (Eclipse Temurin)
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app
EXPOSE 8080

COPY --from=build /target/05-api-gestao-vagas-0.0.1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
