FROM maven:3.8.5-openjdk-17

EXPOSE 8080

WORKDIR /app

# Copiar archivos necesarios
COPY ./pom.xml /app
COPY ./src /app/src

# Ejecutar dependencias y empaquetar la aplicación
RUN mvn dependency:go-offline
RUN mvn package -DskipTests

# Verificar si el archivo jar se ha generado correctamente
RUN ls -l /app/target

# Definir el punto de entrada
ENTRYPOINT ["java", "-jar", "/app/target/ArtesaniasDeNuestraTierra.jar", "--spring.profiles.active=test"]