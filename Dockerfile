#Base image with java 17, it defines the base image from openjdk:17-jdk-slim
#set working directory
FROM maven:3.6.3-openjdk-17 AS build

WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY target .
RUN mvn -f /app clean package -DskipTests
FROM openjdk:17-jdk-slim
#Copy jar file into container
COPY target/case-study-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
#Command to run  your application
CMD ["java", "-jar", "case-study-0.0.1-SNAPSHOT.jar"]
