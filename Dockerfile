# TUTORIAL FROM -> https://viblo.asia/p/build-optimized-docker-images-for-spring-boot-application-38X4EPqoVN2

FROM eclipse-temurin:23
LABEL authors="linhhuynhcoding"
WORKDIR /workspace/
COPY ./target/app-1.0-SNAPSHOT.jar ./app.jar

ENTRYPOINT ["java", "-jar", "./app.jar"]
