FROM eclipse-temurin:21-jre

WORKDIR /usr/app
COPY target/challenge-0.0.1-SNAPSHOT.jar /usr/app/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "challenge-0.0.1-SNAPSHOT.jar"]
