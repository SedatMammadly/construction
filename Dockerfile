FROM openjdk:21-jdk
COPY /build/libs/construction-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]

