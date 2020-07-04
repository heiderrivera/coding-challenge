# ------------------------------------------------------------------------
#
# Dockerfile for Bill Service
# 
# path /opt/ms/coding-challenge/bill-service
#
# docker build -f Dockerfile -t bill-service:0.0.1 .
#
# ------------------------------------------------------------------------

FROM openjdk:8
MAINTAINER heiderrivera@gmail.com

RUN mkdir -p /opt/ms/coding-challenge/bill-service

WORKDIR /opt/ms/coding-challenge/bill-service

# --------------------------------
# Copy scripts, packages & plugins
# --------------------------------
COPY target/bill-0.0.1-SNAPSHOT.jar /opt/ms/coding-challenge/bill-service
COPY src/main/resources/application.yml /opt/ms/coding-challenge/bill-service

#-----------------------
# Expose container ports
# ----------------------
EXPOSE 8082

ENTRYPOINT java -jar -Dspring.profiles.active=prod /opt/ms/coding-challenge/bill-service/bill-0.0.1-SNAPSHOT.jar
