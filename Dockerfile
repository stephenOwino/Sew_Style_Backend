FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/sew_style.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "app.jar"]



