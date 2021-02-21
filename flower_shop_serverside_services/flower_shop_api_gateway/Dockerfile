FROM openjdk:12-jdk-alpine

VOLUME /tmp
ADD target/flower_shop_api_gateway.jar api_gateway_service.jar
ENTRYPOINT ["java", "-Xmx200m", "-jar", "/api_gateway_service.jar"]

EXPOSE 8080