FROM adoptopenjdk/openjdk11:jdk-11.0.9_11-alpine
RUN
COPY target/superhero-service-0.0.1-SNAPSHOT.jar superhero-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/superhero-service-0.0.1-SNAPSHOT.jar"]