FROM openjdk:21-jdk-slim
LABEL authors="alivka"

WORKDIR /work-today-project

COPY target/work-today-project-0.0.1-SNAPSHOT.jar work-today-project.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "work-today-project.jar"]