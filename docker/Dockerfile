FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app

COPY . .

RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim as runtime
WORKDIR /app

COPY --from=build /app/target/*.jar bot.jar

COPY .env .env

COPY data/bot_database.db app/bot_database.db

ENV DISCORD_TOKEN="<SEU TOKEN DO BOT>"
ENV URL_DB="<URL DO BANCO DE DADOS>"

ENTRYPOINT ["java", "-jar", "bot.jar"]