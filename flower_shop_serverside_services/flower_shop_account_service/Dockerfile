
##account_service

FROM openjdk:12-jdk-alpine

VOLUME /tmp
EXPOSE 8080
ADD target/account_service.war account_service.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/account_service.jar"]