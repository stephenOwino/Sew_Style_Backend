FROM openjdk:17-jdk-slim
WORKDIR /myapp
COPY target/Sew_Style_Backend.jar app.jar
EXPOSE 5000
CMD ["java", "-jar", "app.jar"]


