FROM openjdk:17-slim

ARG JAR_FILE=./build/libs/*-SNAPSHOT.jar

COPY ${JAR_FILE} haruhan.jar

# 시스템 시간대 설정
ENV TZ=Asia/Seoul

# JVM 시간대 설정
ENTRYPOINT ["java", "-Duser.timezone=Asia/Seoul", "-jar", "/haruhan.jar"]