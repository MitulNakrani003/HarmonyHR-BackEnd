# Stage 1: Build the application using Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package -DskipTests

# Stage 2: Create the final, lightweight image
FROM openjdk:17-slim
WORKDIR /app
# Copy the built JAR from the 'build' stage
COPY --from=build /app/target/*.jar app.jar
# Expose the port your Spring Boot app runs on
EXPOSE 8080
# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]