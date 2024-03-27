#Base image with java 17, it defines the base image from openjdk:17-jdk-slim
#set working directory
WORKDIR /app
FROM maven:3.6.3-openjdk-11 AS build
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
#Copy jar file into container
COPY target/case-study-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
#Command to run  your application
CMD ["java", "-jar", "case-study-0.0.1-SNAPSHOT.jar"]

#VOLUME /tmp
#EXPOSE 8080
#ARG JAR_FILE=target/case-study-0.1.jar
#ADD ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]



