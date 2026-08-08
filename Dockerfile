# Stage 1: Build the application
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Copy the maven wrapper and pom file first to cache dependencies
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x mvnw

# Copy the source code and build
COPY src src
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy ONLY the built jar from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose standard Spring Boot port
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]