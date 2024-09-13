FROM openjdk:21-jdk-slim
WORKDIR /myapp
COPY target/Sew_StylesBackend.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/myapp/app.jar"]




