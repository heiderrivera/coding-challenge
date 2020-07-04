# ------------------------------------------------------------------------
#
# Dockerfile for Checkout Service
# 
# path /opt/ms/coding-challenge/checkout-service
#
# docker build -f Dockerfile -t checkout-service:0.0.1 .
#
# ------------------------------------------------------------------------

FROM openjdk:8
MAINTAINER heiderrivera@gmail.com

RUN mkdir -p /opt/ms/coding-challenge/checkout-service

WORKDIR /opt/ms/coding-challenge/checkout-service

# --------------------------------
# Copy scripts, packages & plugins
# --------------------------------
COPY target/checkout-0.0.1-SNAPSHOT.jar /opt/ms/coding-challenge/checkout-service
COPY src/main/resources/application.yml /opt/ms/coding-challenge/checkout-service

#-----------------------
# Expose container ports
# ----------------------
EXPOSE 8081

ENTRYPOINT java -jar -Dspring.profiles.active=prod /opt/ms/coding-challenge/checkout-service/checkout-0.0.1-SNAPSHOT.jar
