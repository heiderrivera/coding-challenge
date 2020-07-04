# ------------------------------------------------------------------------
#
# Dockerfile for Logistic Service
# 
# path /opt/ms/coding-challenge/logistic-service
#
# docker build -f Dockerfile -t logistic-service:0.0.1 .
#
# ------------------------------------------------------------------------

FROM openjdk:8
MAINTAINER heiderrivera@gmail.com

RUN mkdir -p /opt/ms/coding-challenge/logistic-service

WORKDIR /opt/ms/coding-challenge/logistic-service

# --------------------------------
# Copy scripts, packages & plugins
# --------------------------------
COPY target/logistic-0.0.1-SNAPSHOT.jar /opt/ms/coding-challenge/logistic-service
COPY src/main/resources/application.yml /opt/ms/coding-challenge/logistic-service

#-----------------------
# Expose container ports
# ----------------------
EXPOSE 8083

ENTRYPOINT java -jar -Dspring.profiles.active=prod /opt/ms/coding-challenge/logistic-service/logistic-0.0.1-SNAPSHOT.jar
