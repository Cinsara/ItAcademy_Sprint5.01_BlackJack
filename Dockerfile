# Usamos la imagen oficial de Eclipse Temurin (OpenJDK) con JDK 21
FROM eclipse-temurin:21-jdk as builder

# Directorio de trabajo
WORKDIR /app

# --- Fase de ejecución (imagen final liviana) ---
FROM eclipse-temurin:21-jre

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]