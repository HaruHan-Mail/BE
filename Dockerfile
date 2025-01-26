FROM openjdk:17-slim

ARG JAR_FILE=./build/libs/*-SNAPSHOT.jar

COPY ${JAR_FILE} haruhan.jar

ENTRYPOINT ["java", "-jar", "/haruhan.jar"]