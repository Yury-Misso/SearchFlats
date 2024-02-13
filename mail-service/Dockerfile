# Базовый образ Java 17
FROM eclipse-temurin:17.0.9_9-jre-alpine

LABEL authors="Misso"

ADD ./target/mail-service-1.0.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/mail-service-1.0.jar"]

EXPOSE 8060