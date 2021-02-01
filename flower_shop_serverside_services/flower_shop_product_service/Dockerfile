
#### product_service
FROM openjdk:12-jdk-alpine

VOLUME /tmp
EXPOSE 8080
ADD target/product_service.war product_service.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/product_service.jar"]