# Step 1: Build stage using Maven and Eclipse Temurin Java 17
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Runtime stage
FROM eclipse-temurin:17-jdk-jammy
COPY --from=build /target/*.jar app.jar
EXPOSE 8080

# Explicitly maps the environment variables directly into the Spring app properties on startup
ENTRYPOINT ["java", "-Dspring.datasource.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}?useSSL=true&trustServerCertificate=true&sslMode=REQUIRED&allowPublicKeyRetrieval=true", "-Dspring.datasource.username=${DB_USER}", "-Dspring.datasource.password=${DB_PASSWORD}", "-jar", "app.jar"]