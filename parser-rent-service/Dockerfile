# Базовый образ Java 17
FROM eclipse-temurin:17-jre-alpine

LABEL authors="Misso"

# Копирование собранного jar-файла в образ
COPY target/parser-rent-service-1.0.jar app.jar

# Задаем точку входа для запуска приложения
ENTRYPOINT ["java","-jar","/app.jar"]