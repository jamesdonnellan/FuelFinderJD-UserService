FROM eclipse-temurin:23-jre AS runtime
WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]