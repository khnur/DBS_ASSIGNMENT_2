FROM openjdk:17-oracle
VOLUME /tmp
LABEL authors="Nurzhan Kozhamuratov"

COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080