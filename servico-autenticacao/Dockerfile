FROM openjdk:21-bookworm AS build
WORKDIR /app
COPY . .
RUN ./gradlew clean assemble

FROM openjdk:21-bookworm
RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/* && \
    useradd -m appuser

USER appuser
COPY --from=build /app/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]