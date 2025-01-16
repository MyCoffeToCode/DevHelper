FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app

COPY . .

RUN mvn package -DskipTests

FROM openjdk:17-jdk-slim as runtime
WORKDIR /app

COPY --from=build /app/target/*.jar bot.jar

COPY .env .env

COPY data/bot_database.db app/bot_database.db

ENV DISCORD_TOKEN=""
ENV URL_DB=""

ENTRYPOINT ["java", "-jar", "bot.jar"]