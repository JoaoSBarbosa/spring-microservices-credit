#FROM maven:3.8.5-openjdk-17 as build
#WORKDIR /app
#COPY . .
#RUN mvn clean package -DskipTests
#
#
#FROM openjdk:17
#WORKDIR /app
#COPY --from=build ./app/target/*jar ./app.jar
#
#ARG EUREKA_SERVER=localhost
#ARG KEYCLOAK_SERVER=localhost
#ARG KEYCLOAK_PORT=8081
#ENTRYPOINT java -jar app.jargp

# Fase de build
FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Fase de runtime
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*jar ./app.jar

ENV EUREKA_SERVER=${EUREKA_SERVER:-localhost}
ENV KEYCLOAK_SERVER=${KEYCLOAK_SERVER:-localhost}
ENV KEYCLOAK_PORT=${KEYCLOAK_PORT:-8081}

ENTRYPOINT ["java", "-jar", "app.jar"]
