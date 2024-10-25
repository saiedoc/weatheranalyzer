# Stage 1: Build the application
FROM maven:3.8.1-openjdk-17 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn package

# Stage 2: Run the application
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/weatheranalyzer-1.0-SNAPSHOT.jar /app/weatheranalyzer.jar

ENTRYPOINT ["java", "-jar", "/app/weatheranalyzer.jar"]
