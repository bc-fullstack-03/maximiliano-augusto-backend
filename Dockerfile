FROM eclipse-temurin:17-jdk-alpine
ADD target/parrot-* parrot.jar
ENTRYPOINT ["java", "-jar", "/parrot.jar"]