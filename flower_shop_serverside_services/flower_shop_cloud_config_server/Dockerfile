FROM openjdk:12-jdk-alpine


VOLUME /tmp
ADD target/flower_shop_cloud_config_server.jar config_service.jar
ENTRYPOINT ["java", "-Xmx200m", "-jar", "/config_service.jar"]

HEALTHCHECK --interval=30s --timeout=30s CMD curl -f http://localhost:8888/actuator/health || exit 1
EXPOSE 8888