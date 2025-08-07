FROM maven:3.9.9-amazoncorretto-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:21.0.5
WORKDIR /app
COPY --from=builder /app/target/quarkus-app/ /app/

EXPOSE 8080
ENV TZ=America/Sao_Paulo
ENTRYPOINT ["java", "--enable-preview", "-jar", "/app/quarkus-run.jar"]
