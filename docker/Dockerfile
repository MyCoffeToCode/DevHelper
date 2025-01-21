FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim as runtime
WORKDIR /app

COPY --from=build /app/target/*.jar bot.jar

COPY --from=build /app/target/classes /app/classes

COPY data/bot_database.db app/bot_database.db

ENTRYPOINT ["java", "-cp", "/app/classes:/app/bot.jar", "DevHelper.MainBot"]