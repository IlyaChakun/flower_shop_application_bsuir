FROM openjdk:12-jdk-alpine


VOLUME /tmp
ADD target/flower_shop_discovery_server.jar discovery_service.jar
ENTRYPOINT ["java", "-Xmx200m", "-jar", "/discovery_service.jar"]

EXPOSE 8761