FROM eclipse-temurin:23-jre AS runtime
WORKDIR /app

COPY target/*.jar app.jar

LABEL authors="james"

EXPOSE 8000
ENTRYPOINT ["java", "-jar", "/app/app.jar"]