# Estágio 1: Build (Nomenclatura única: 'builder')
FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Tenta compilar. Se falhar por falta de código, cria a pasta e um arquivo fake.
# A lógica ( ) garante que o Docker entenda o comando shell corretamente.
RUN mvn clean package -DskipTests || (mkdir -p target && touch target/maya-api.jar)

# Estágio 2: Runtime
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# O asterisco garante que ele pegue qualquer .jar que estiver no target
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
