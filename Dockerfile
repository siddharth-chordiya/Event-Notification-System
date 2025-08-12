
# Step 1: Build stage (uses Maven inside Docker)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package spring-boot:repackage -DskipTests

# Step 2: Runtime stage (runs only the built JAR)
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/Notification-Service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
