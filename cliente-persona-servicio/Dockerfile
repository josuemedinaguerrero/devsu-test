FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/cliente-persona-servicio-0.0.1-SNAPSHOT.jar /app/microservice.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/app/microservice.jar" ]
