FROM eclipse-temurin:17.0.12_7-jdk

EXPOSE 8080

WORKDIR /app

COPY ./pom.xml /app
COPY ./src /app/src
COPY .mvn /app/.mvn
COPY ./mvnw /app

RUN ./mvnw dependency:go-offline
RUN ./mvnw package -DskipTests

ENTRYPOINT [ "java", "-jar", "/app/target/ArtesaniasDeNuestraTierra.jar", "--spring.profiles.active=test"]