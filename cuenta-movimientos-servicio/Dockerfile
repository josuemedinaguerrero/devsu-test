FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/cuenta-movimientos-servicio-0.0.1-SNAPSHOT.jar /app/microservice.jar

EXPOSE 8081

ENTRYPOINT [ "java", "-jar", "/app/microservice.jar" ]
