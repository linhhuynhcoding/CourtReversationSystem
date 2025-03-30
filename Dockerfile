FROM oraclelinux:9-slim
LABEL authors="linhhuynhcoding"

ENTRYPOINT ["top", "-b"]

WORKDIR /server

COPY pom.xml .
COPY ./src ./src