FROM eclipse-temurin:17

LABEL mentainer="vladproduction.net@gmail.com"

WORKDIR /app

COPY target/WookieBooks-0.0.1-SNAPSHOT.jar /app/WookieBooks.jar

ENTRYPOINT ["java", "-jar", "WookieBooks.jar"]