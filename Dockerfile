FROM maven:3.8.1-openjdk-17-slim AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn clean install

FROM openjdk:17-slim

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/pruebatecnica-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "pruebatecnica-0.0.1-SNAPSHOT.jar"]