FROM maven:3.6.3-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -f /app clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/case-study-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "case-study-0.0.1-SNAPSHOT.jar"]

