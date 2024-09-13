

FROM openjdk:21-jdk-slim
WORKDIR /myapp
COPY target/Sew_Style_Backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/myapp/app.jar"]



