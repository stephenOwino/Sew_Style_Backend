# Use the official OpenJDK 21 image from the Docker Hub
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /myapp

# Copy the jar file into the container
COPY target/SewStylesBackendApplication.jar app.jar

# Expose the port on which the app will run
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

