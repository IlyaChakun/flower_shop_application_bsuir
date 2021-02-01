
##account_service


#
# Build stage
#
#FROM maven:3.6.0-jdk-11-slim AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean package


#
# Package stage
#
FROM openjdk:12-jdk-alpine

VOLUME /tmp
EXPOSE 8080
ADD target/account_service.war account_service.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/account_service.jar"]