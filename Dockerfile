FROM oraclelinux:9-slim
LABEL authors="linhhuynhcoding"
COPY ./target/app-1.0-SNAPSHOT.jar ./app.jar

ENTRYPOINT ["java", "-jar", "./app.jar"]
