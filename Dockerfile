FROM maven:3.9.2-eclipse-temurin-17

RUN apt-get update && apt-get install -qq -y --no-install-recommends

ENV INSTALL_PATH /payment

RUN mkdir $INSTALL_PATH

WORKDIR $INSTALL_PATH

COPY . .