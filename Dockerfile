FROM maven:3.8.1-adoptopenjdk-16 AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:16-jdk-slim
COPY --from=build /usr/src/app/target/ImpfenSaarlandBot-1.0-SNAPSHOT-shaded.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]