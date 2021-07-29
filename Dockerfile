FROM openjdk:11-oracle
ENTRYPOINT ["java","-Dspring.profiles.active=qa","-jar","test-api-0.0.1.jar"]
COPY target/test-api-0.0.1.jar .