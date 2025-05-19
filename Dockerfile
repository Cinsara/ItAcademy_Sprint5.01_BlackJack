# Fase 1: Build (compila el proyecto con Gradle)
FROM gradle:8.5-jdk21 AS builder

WORKDIR /app

# Copiamos todo el proyecto
COPY . .

# Compilamos el proyecto y generamos el .jar
RUN gradle build -x test --no-daemon

# Fase 2: Imagen final liviana con solo el .jar
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copiamos el .jar desde la fase anterior
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=docker

ENTRYPOINT ["java", "-jar", "app.jar"]
