# Stage 1: Build the application
# Use a Maven image to build the JAR file.
FROM maven:3.8.4-openjdk-17-slim AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Package the application into a JAR file
RUN mvn clean package -DskipTests

# ---
# Stage 2: Run the application
# Use a lightweight JDK image to run the JAR.
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Expose port 8080 to the outside world
EXPOSE 8080

# Explicitly create the log directory and set permissions
RUN mkdir -p /var/log/app && chown -R 1000:1000 /var/log/app

# Set the user to a non-root user (good practice for security)
USER 1000

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Define the command to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]

# The logback-spring.xml file points to /var/log/app, which will be created when the container runs
# This is where your logs will go.