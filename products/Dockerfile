FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/dropwizard-guice-service-1.0-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar", "server", "config.yml"]
EXPOSE 8080
