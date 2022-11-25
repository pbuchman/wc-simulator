FROM openjdk:17-alpine

COPY build/libs/simulator-service-0.0.1-SNAPSHOT.jar /simulator-service/simulator-service.jar

WORKDIR /simulator-service

ENV PROFILE=docker

CMD ["java", "-jar", "simulator-service.jar", "-Dreactor.netty.http.server.accessLogEnabled=true"]
