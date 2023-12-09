FROM openjdk:17-jdk-alpine
LABEL maintainer="Isaias Villarreal isaiasvillarreal0225@gmail.com"
VOLUME /tmp
ADD target/spring-boot-data-jpa-1.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

